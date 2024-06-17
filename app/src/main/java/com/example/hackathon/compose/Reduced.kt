package com.example.hackathon.compose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hackathon.R
import com.example.hackathon.tescoFontFamily
import com.example.hackathon.ui.theme.HackathonTheme

@Composable
fun Reduced() {
    Log.i("Reduced", "reduced render ")
    Row(modifier = Modifier.fillMaxWidth()) {

        Column(modifier = Modifier.padding(end = 3.dp)) {

            Box(modifier = Modifier
                .clip(RoundedCornerShape(topStart = 10.dp))
                .clip(RoundedCornerShape(bottomStart = 10.dp))
                .clip(RoundedCornerShape(topEnd = 10.dp))
                .clip(RoundedCornerShape(bottomEnd = 10.dp))
                .background(colorResource(R.color.tesco_orange))
                .fillMaxWidth()
            ) {
                Column(
//                    modifier = Modifier.size(65.dp, 60.dp),
                    modifier = Modifier.height(45.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {

                            Text(
                                "Reduced to Clear",
                                color = Color.Black,
                                fontSize = 15.sp,
                                fontFamily = tescoFontFamily,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )

                    }

                }
            }
        }

    }
}

@Composable
@Preview
fun ReducedView() {
    HackathonTheme {
        Reduced(
//            Promotion(
//                promotionid = "123",
//                offertext = "This is a wonderful offer - cheapest product available in tesco",
//                attributes = "CLUBCARD_PRICING",
//                promotiontype = "",
//                startdate = "",
//                enddate = "",
//                productid = "",
//                afterdiscount = "",
//                beforediscount = "",
//                unitsellinginfo = ""
//            )
        )
    }
}