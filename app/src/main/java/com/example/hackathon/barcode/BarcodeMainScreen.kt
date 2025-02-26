package com.example.hackathon.barcode

import android.Manifest
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.hackathon.compose.LoadingScreen
import com.example.hackathon.viewmodel.AppViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun BarcodeMainScreen(viewModel: AppViewModel) {

    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
    val analyserType by remember { mutableStateOf(AnalyserType.BARCODE) }
    val loader = viewModel.isLoading.value

    if (cameraPermissionState.status.isGranted) {
        if (loader) {
            LoadingScreen()
        } else {
            CameraScreen(analyserType, viewModel)
        }
    } else if (cameraPermissionState.status.shouldShowRationale) {
        Text("Camera Permission permanently denied")
    } else {
        SideEffect {
            cameraPermissionState.run { launchPermissionRequest() }
        }
        Text("No Camera Permission")
    }
}
