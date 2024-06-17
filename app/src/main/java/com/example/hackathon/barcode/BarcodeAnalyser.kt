package com.example.hackathon.barcode

import android.annotation.SuppressLint
import android.content.Context
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
import kotlinx.coroutines.launch
import retrofit2.HttpException

class BarcodeAnalyser(private val context: Context,
                      private val viewModel: AppViewModel
) : ImageAnalysis.Analyzer {

    private val options = BarcodeScannerOptions.Builder()
        .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
        .build()

    private val scanner = BarcodeScanning.getClient(options)

    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        if (!viewModel.isScanEnabled.value) {
            imageProxy.close()
            return
        }
        imageProxy.image?.let { image ->
            scanner.process(
                InputImage.fromMediaImage(
                    image, imageProxy.imageInfo.rotationDegrees
                )
            ).addOnSuccessListener { barcode ->
                barcode?.takeIf { it.isNotEmpty() }
                    ?.mapNotNull { it.rawValue }
                    ?.joinToString(",")
                    ?.let { value ->
                        viewModel.enableScan(false)
                        getAlternatives(value) }
            }.addOnCompleteListener {
                Log.i("barcodeAnalyser", "camera closed")
                imageProxy.close()
            }.addOnFailureListener {
                Log.e("barcodeAnalyser", "camera error in processing")
                viewModel.enableScan(true)
            }
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
                            Log.i("barcodeAnalyser", "Result received: " + result.body()!!.message )
                            viewModel.hideLoader()
                            viewModel.updateResult(it)
                            viewModel.showError(false)
                        }
                    } else {
                        viewModel.hideLoader()
//                        viewModel.enableScan(true)
                        viewModel.showError(true)
                        val statusCode = result.code()
                        val errorBody = result.errorBody()?.string()
                        Log.e("GET_ERROR", "Unsuccessful response: $statusCode - $errorBody")
                    }
                } catch (e: HttpException) {
                    viewModel.hideLoader()
//                    viewModel.enableScan(true)
                    viewModel.showError(true)
                    val statusCode = e.code()
                    val errorMessage = e.message()
                    Log.e("HTTP_EXCEPTION", "HTTP error: $statusCode - $errorMessage")
                } catch (e: Throwable) {
                    // Handle network exceptions or unexpected errors
                    viewModel.hideLoader()
//                    viewModel.enableScan(true)
                    viewModel.showError(true)
                    Log.e("GET_FAILURE", "Error fetching data: $e.message")
                }
            }
    }
}
