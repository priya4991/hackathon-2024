package com.example.hackathon.compose

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.hackathon.R
import com.example.hackathon.model.ItemSku
import com.example.hackathon.tescoFontFamily
import com.example.hackathon.ui.theme.HackathonTheme

@Composable
fun SkuView(sku: ItemSku) {
    Column {


    Row(verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            model = sku.defaultimageurl,
            contentDescription = ""
        )
        Column(modifier = Modifier.padding(all = 10.dp)) {

            Row(modifier = Modifier.padding(bottom = 5.dp)) {
                Text(
                    sku.title,
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
                    sku.price?.let {
                        Text(
                            "\u00A3$it",
                            fontSize = 25.sp,
                            fontFamily = tescoFontFamily,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Row {
                        sku.unitprice?.let {
                            Text(
                                "\u00A3$it",
                                color = Color.Gray,
                                fontSize = 12.sp,
                                fontFamily = tescoFontFamily,
                                fontWeight = FontWeight.Normal
                            )
                        }
                        sku.unitofmeasure?.let {
                            Text(
                                "/$it",
                                color = Color.Gray,
                                fontSize = 12.sp,
                                fontFamily = tescoFontFamily,
                                fontWeight = FontWeight.Normal
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1.0f))

                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.tesco_blue)),
                    onClick = { },
                    modifier = Modifier.size(110.dp, 40.dp)
                ) {
                    Text(
                        "Add",
                        color = Color.White,
                        fontFamily = tescoFontFamily,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

    }
        Promotions(sku)
}
}

@Preview(showBackground = true)
@Composable
fun SkuViewPreview() {
    HackathonTheme {
        SkuView(
            ItemSku(
                "251496258",
            "Pepsi Max No Sugar Cola Bottle 500ml",
        "PEPSI",
        "00000087170702",
        "https://digitalcontent.api.tesco.com/v2/media/ghs/46c9b712-9299-434c-9408-4d6e912935e7/dafd37ae-69b4-4589-85b3-6879a9edaad2_1516733458.jpeg?h=225&w=225",
        "RHJpbmtzJTdDT24lMjBUaGUlMjBHbyUyMERyaW5rcyU3Q0Zpenp5JTIwJiUyMFNvZnQlMjBEcmlua3M=",
                "Fizzy & Soft Drinks",
       "RHJpbmtzJTdDT24lMjBUaGUlMjBHbyUyMERyaW5rcyU3Q0Zpenp5JTIwJiUyMFNvZnQlMjBEcmlua3MlN0NGaXp6eSUyMCYlMjBTb2Z0JTIwRHJpbmtz",
                "Fizzy & Soft Drinks",
        "1.55",
        "0.31",
        "100ml",
      "AvailableForSale",
                emptyList()
            )
        )
    }
}