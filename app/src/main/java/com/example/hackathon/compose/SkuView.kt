package com.example.hackathon.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hackathon.R
import com.example.hackathon.model.Sku
import com.example.hackathon.tescoFontFamily
import com.example.hackathon.ui.theme.HackathonTheme
import java.util.UUID

@Composable
fun SkuView(sku: Sku) {

    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = sku.image),
            contentDescription = sku.name
        )
        Column(modifier = Modifier.padding(all=10.dp)) {

            Row(modifier = Modifier.padding(bottom = 5.dp)) {
                Text(
                    sku.name,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Left,
                    color = colorResource(id = R.color.tesco_blue),
                    fontFamily = tescoFontFamily,
                    fontWeight = FontWeight.Bold,
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        sku.price,
                        fontSize = 25.sp,
                        fontFamily = tescoFontFamily,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        sku.pricePr,
                        color = Color.Gray,
                        fontSize = 12.sp,
                        fontFamily = tescoFontFamily,
                        fontWeight = FontWeight.Normal
                    )
                }

                Spacer(modifier = Modifier.weight(1.0f))

                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.tesco_blue)),
                    onClick = {  },
                    modifier = Modifier.size(110.dp, 40.dp)
                ) {
                    Text(
                        "Add",
                        fontFamily = tescoFontFamily,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SkuViewPreview() {
    HackathonTheme {
        SkuView(
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