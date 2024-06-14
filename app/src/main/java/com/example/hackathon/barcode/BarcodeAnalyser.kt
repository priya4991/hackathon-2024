package com.example.hackathon.barcode

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.example.hackathon.api.RetrofitHelper
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BarcodeAnalyser(private val context: Context) : ImageAnalysis.Analyzer {

    private val options = BarcodeScannerOptions.Builder()
        .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
        .build()

    private val scanner = BarcodeScanning.getClient(options)

    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        imageProxy.image?.let { image ->
            scanner.process(
                InputImage.fromMediaImage(
                    image, imageProxy.imageInfo.rotationDegrees
                )
            ).addOnSuccessListener { barcode ->
                barcode?.takeIf { it.isNotEmpty() }
                    ?.mapNotNull { it.rawValue }
                    ?.joinToString(",")
                    ?.let { getAlternatives(it) }
            }.addOnCompleteListener {
                imageProxy.close()
            }
        }
    }

    private fun getAlternatives(text: String) {
//        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        GlobalScope.launch(Dispatchers.Main) {
            val result = RetrofitHelper.getInstance().getAlternatives();
            if (result != null) {
                Toast.makeText(context, result.body()?.punchline, Toast.LENGTH_SHORT).show()
//                Prompt(sku = )
            }
        }
    }
}
