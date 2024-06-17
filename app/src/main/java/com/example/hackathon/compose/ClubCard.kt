package com.example.hackathon.compose

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hackathon.R
import com.example.hackathon.model.Promotion
import com.example.hackathon.tescoFontFamily
import com.example.hackathon.ui.theme.HackathonTheme

@Composable
fun ClubCard(promo: Promotion) {
    Log.i("Clubcard", "clubcard render " + promo.productid)
    Row(modifier = Modifier.fillMaxWidth().wrapContentHeight()) {

        Column(modifier = Modifier.padding(end = 3.dp)) {

            Box(modifier = Modifier
                .clip(RoundedCornerShape(topStart = 10.dp))
                .clip(RoundedCornerShape(bottomStart = 10.dp))
                .background(colorResource(R.color.tesco_blue))
            ) {
                Column(
                    modifier = Modifier.size(70.dp, 65.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        promo.attributes?.let {
                            Text(
                                "Clubcard Price",
                                color = Color.White,
                                fontSize = 10.sp,
                                fontFamily = tescoFontFamily,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                lineHeight = 10.sp
                            )
                        }
                    }

                }
            }
        }

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topEnd = 10.dp))
                .clip(RoundedCornerShape(bottomEnd = 10.dp))
                .background(colorResource(R.color.tesco_orange))
                .height(65.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .background(colorResource(R.color.tesco_orange))
                    .padding(all = 7.dp)
            )
            {
                Spacer(modifier = Modifier.weight(1.0f))
//                for (line in sku.clubcardOffer) {
                promo.offertext?.let {
                    val trimmedText = if (it.contains("Meal Deal")) {
                        val dashIndex = it.indexOf(" - ")
                        if (dashIndex != -1) {
                            it.substring(0, dashIndex).trim()
                        } else {
                            it
                        }
                    } else {
                        it
                    }
                    Text(
                        trimmedText,
                        fontFamily = tescoFontFamily,
                        fontWeight = FontWeight.Bold,
//                        maxLines = 2,
                        overflow = TextOverflow.Clip,
                        fontSize = 13.sp,
                        lineHeight = 15.sp
                    )
                }
//                }
                Spacer(modifier = Modifier.weight(1.0f))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ClubCardPreview() {
    HackathonTheme {
        ClubCard(
            Promotion(
                promotionid = "123",
                offertext = "Any 2 for £4.50 Clubcard Price - Selected Pepsi Max Or Pepsi Diet 330ml",
                attributes = "CLUBCARD_PRICING",
                promotiontype = "",
                startdate = "",
                enddate = "",
                productid = "",
                afterdiscount = "",
                beforediscount = "",
                unitsellinginfo = ""
            )
        )
    }
}