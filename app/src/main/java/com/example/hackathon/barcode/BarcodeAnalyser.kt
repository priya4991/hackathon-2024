package com.example.hackathon.barcode

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.ImageFormat
import android.graphics.YuvImage
import android.util.Base64
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.example.hackathon.BuildConfig
import com.example.hackathon.api.RetrofitHelper
import com.example.hackathon.viewmodel.AppViewModel
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.ByteArrayOutputStream

class BarcodeAnalyser(
    private val viewModel: AppViewModel
) : ImageAnalysis.Analyzer {

    private val options = BarcodeScannerOptions.Builder()
        .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
        .build()

    private val scanner = BarcodeScanning.getClient(options)
    private var retryCount = 0
    private val maxRetries = 3
    private val barcodeValues = mutableListOf<String>()
    private val framesToCapture = 5
    private val frameDelayMillis = 200L // Delay between frames in milliseconds

    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        if (!viewModel.isScanEnabled.value) {
            imageProxy.close()
            return
        }

        imageProxy.image?.let { image ->
            val rotationDegrees = imageProxy.imageInfo.rotationDegrees
            InputImage.fromMediaImage(image, rotationDegrees)

            // Convert the image to Bitmap for preprocessing
            val bitmap = imageToBitmap(imageProxy)
            val processedImage = preprocessImage(bitmap)

            scanner.process(InputImage.fromBitmap(processedImage, rotationDegrees))
                .addOnSuccessListener { barcodes ->
                    barcodes?.takeIf { it.isNotEmpty() }
                        ?.mapNotNull { validateBarcode(it) }
                        ?.forEach { value ->
                            barcodeValues.add(value)
                        }

                    if (barcodeValues.size >= framesToCapture) {
                        val mostCommonValue = barcodeValues.groupingBy { it }.eachCount()
                            .maxByOrNull { it.value }?.key
                        if (mostCommonValue != null) {
                            viewModel.enableScan(false)
                            getAlternatives(mostCommonValue)
                            barcodeValues.clear() // Clear the list for the next scan
                        }
                    }
                }
                .addOnCompleteListener {
                    imageProxy.close()
                    if (barcodeValues.size < framesToCapture) {
                        GlobalScope.launch(Dispatchers.Main) {
                            delay(frameDelayMillis)
                            viewModel.enableScan(true)
                        }
                    }
                }
                .addOnFailureListener {
                    handleScanFailure()
                }
        }
    }

    private fun imageToBitmap(imageProxy: ImageProxy): Bitmap {
        val yBuffer = imageProxy.planes[0].buffer
        val uBuffer = imageProxy.planes[1].buffer
        val vBuffer = imageProxy.planes[2].buffer

        val ySize = yBuffer.remaining()
        val uSize = uBuffer.remaining()
        val vSize = vBuffer.remaining()

        val nv21 = ByteArray(ySize + uSize + vSize)

        yBuffer.get(nv21, 0, ySize)
        uBuffer.get(nv21, ySize, uSize)
        vBuffer.get(nv21, ySize + uSize, vSize)

        val yuvImage = YuvImage(nv21, ImageFormat.NV21, imageProxy.width, imageProxy.height, null)
        val out = ByteArrayOutputStream()
        yuvImage.compressToJpeg(
            android.graphics.Rect(0, 0, imageProxy.width, imageProxy.height),
            100,
            out
        )
        val imageBytes = out.toByteArray()
        return android.graphics.BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }

    private fun preprocessImage(bitmap: Bitmap): Bitmap {
        // Example preprocessing: Apply grayscale filter or enhance contrast
        // Here we return the original bitmap, but you can add preprocessing steps
        return bitmap
    }

    private fun validateBarcode(barcode: Barcode): String? {
        val rawValue = barcode.rawValue ?: return null

        // Example validation: Check if the barcode matches expected formats
        if (rawValue.matches(Regex("^[0-9]{8,13}\$"))) { // Assuming barcode is numeric with length 8 to 13
            return rawValue
        }

        return null
    }

    private fun handleScanFailure() {
        if (retryCount < maxRetries) {
            retryCount++
            Log.w("BarcodeAnalyser", "Retrying scan... ($retryCount/$maxRetries)")
            viewModel.enableScan(true) // Allow retry
        } else {
            Log.e("BarcodeAnalyser", "Max retries reached. Scan failed.")
            viewModel.enableScan(false)
            viewModel.showError(true)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getAlternatives(text: String) {
        Log.i("BarcodeAnalyser", "GTIN: $text")
        GlobalScope.launch(Dispatchers.Main) {
            val username = BuildConfig.USERNAME
            val password = BuildConfig.PASSWORD
            val auth = "$username:$password"
            val encodedAuth = Base64.encodeToString(auth.toByteArray(), Base64.NO_WRAP)
            val authHeader = "Basic $encodedAuth"
            Log.i("barcodeAnalyser", "sending request")
            viewModel.showLoader()
            try {
                val result = RetrofitHelper.getInstance().getResponse(
                    authHeader,
                    text.padStart(14, '0'),
                    "0"
                )
                if (result.isSuccessful) {
                    result.body()?.let {
                        Log.i("barcodeAnalyser", "Result received: " + result.body()!!.message)
                        viewModel.hideLoader()
                        viewModel.updateResult(it)
                        viewModel.showError(false)
                    }
                } else {
                    viewModel.hideLoader()
                    viewModel.showError(true)
                    val statusCode = result.code()
                    val errorBody = result.errorBody()?.string()
                    Log.e("GET_ERROR", "Unsuccessful response: $statusCode - $errorBody")
                }
            } catch (e: HttpException) {
                viewModel.hideLoader()
                viewModel.showError(true)
                val statusCode = e.code()
                val errorMessage = e.message()
                Log.e("HTTP_EXCEPTION", "HTTP error: $statusCode - $errorMessage")
            } catch (e: Throwable) {
                viewModel.hideLoader()
                viewModel.showError(true)
                Log.e("GET_FAILURE", "Error fetching data: ${e.message}")
            }
        }
    }
}
