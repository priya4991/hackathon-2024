package com.example.hackathon.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import com.example.hackathon.R
import com.example.hackathon.barcode.BarcodeMainScreen
import com.example.hackathon.model.Sku
import com.example.hackathon.ui.theme.HackathonTheme
import java.util.UUID

@Composable
fun Hackathon(sku: Sku) {

    Box(modifier = Modifier
        .zIndex(1f)
        .fillMaxSize()) {
        Box(modifier = Modifier
            .zIndex(1f)
            .fillMaxSize()) {

            Column(verticalArrangement = Arrangement.Center) {
                CameraHeader()
                BarcodeMainScreen()
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
        Hackathon(
            Sku(
                UUID.randomUUID(),
                "Dr Pepper Regular 500 M",
                "£1.69",
                "£0.34/100ml",
                R.drawable.drpepper,
                valid = "Valid for deliver until 23/06",
                priceMatched = false,
                clubcardOffer = arrayListOf("£5 Meal Deal Clubcard Price £5.50","Meal Deal Regular Price - Selected")
            )
        )
    }
}