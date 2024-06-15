package com.example.hackathon.compose

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hackathon.api.ApiResponse
import com.example.hackathon.barcode.BarcodeMainScreen
import com.example.hackathon.barcode.ResultViewModel
import com.example.hackathon.ui.theme.HackathonTheme

@SuppressLint("RememberReturnType")
@Composable
fun Hackathon() {

    val viewModel: ResultViewModel = viewModel(
        key = "resultViewModelKey",
        factory = ViewModelProvider.NewInstanceFactory()
    )
    val result by viewModel.result.observeAsState()
    val alternatives = remember { mutableStateOf<ApiResponse?>(null) }
    Log.i("Hackarthon", "Prompt created")



    result?.let { newResult ->
//        if (!alternatives.contains(newResult)) {
//            Log.i("Hackarthon", "result rendering " + result!!.punchline)
//            alternatives.add(newResult)
//        }
        alternatives.value = newResult
    }

    val closeList: () -> Unit = {
        Log.i("Hackathon", "closing list")
        viewModel.clearResult()
        alternatives.value = null
    }
    Box(modifier = Modifier
        .zIndex(1f)
        .fillMaxSize()) {
        Box(modifier = Modifier
            .zIndex(1f)
            .fillMaxSize()) {

            Column(verticalArrangement = Arrangement.Center) {
                if (alternatives.value == null) {
                    CameraHeader()
                    BarcodeMainScreen(viewModel)
                } else {
                    ShoppingList(closeList)
                    Prompt(alternatives.value!!)
                }
            }

        }

//        Column(modifier = Modifier
//            .fillMaxSize()
//            .padding()
//            .zIndex(2f)
//            .padding(all = 11.dp)) {
//            Spacer(modifier = Modifier.weight(1.0f))
//            Box(modifier = Modifier.padding(top = 140.dp)) {
//                Prompt(sku)
//                BarcodeMainScreen()
//            }
//            Spacer(modifier = Modifier.weight(1.0f))
//        }
    }
}



@Preview(showBackground = true)
@Composable
fun HackathonPreview() {
    HackathonTheme {
        Hackathon()
    }
}