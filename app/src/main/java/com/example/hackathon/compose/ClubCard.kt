package com.example.hackathon.compose

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
import com.example.hackathon.model.Sku
import com.example.hackathon.tescoFontFamily
import com.example.hackathon.ui.theme.HackathonTheme
import java.util.UUID

@Composable
fun ClubCard(sku: Sku) {
    Row(modifier = Modifier.fillMaxWidth()) {

        Column(modifier = Modifier.padding(end = 3.dp)) {

            Box(modifier = Modifier
                .clip(RoundedCornerShape(topStart = 10.dp))
                .clip(RoundedCornerShape(bottomStart = 10.dp))
                .background(colorResource(R.color.tesco_blue))
            ) {
                Column(
                    modifier = Modifier.size(65.dp, 60.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(
                            "Clubcard",
                            color = Color.White,
                            fontSize = 13.sp,
                            fontFamily = tescoFontFamily,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                    Row {
                        Text(
                            "Price",
                            color = Color.White,
                            fontSize = 13.sp,
                            fontFamily = tescoFontFamily,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topEnd = 10.dp))
                .clip(RoundedCornerShape(bottomEnd = 10.dp))
                .background(colorResource(R.color.tesco_orange))
                .height(60.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .background(colorResource(R.color.tesco_orange))
                    .padding(all = 5.dp)
            )
            {
                Spacer(modifier = Modifier.weight(1.0f))
                for (line in sku.clubcardOffer) {
                    Text(
                        line,
                        fontFamily = tescoFontFamily,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 15.sp,
                    )
                }
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