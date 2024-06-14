package com.example.hackathon.compose

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hackathon.R
import com.example.hackathon.api.AlternativesApi
import com.example.hackathon.api.AlternativeItem
import com.example.hackathon.api.RetrofitHelper
import com.example.hackathon.model.Sku
import com.example.hackathon.tescoFontFamily
import com.example.hackathon.ui.theme.HackathonTheme
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.UUID

@OptIn(DelicateCoroutinesApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Prompt(sku: Sku) {

    val alternatives = remember { mutableStateListOf<AlternativeItem>() }

    if (alternatives.isEmpty()) {

        GlobalScope.launch {
//            val result = alternativesApi.getAlternatives(sku.skuId)
            val result = RetrofitHelper.getInstance().getAlternatives()
            if (result?.body() != null) {
                alternatives.add(result.body()!!)
            }
        }
    }

    Box(modifier = Modifier
        .background(Color.White)
        .padding(all = 10.dp)
    ) {
        Column(modifier = Modifier.background(Color.White)) {

            SkuView(sku = sku)

            if (sku.clubcardOffer.isNotEmpty()) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 10.dp)
                        .fillMaxWidth()
                ) {
                    ClubCard(sku)
                }
            }

            Row {
                Column {
                    Text(
                        sku.valid,
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.Gray,
                        fontSize = 10.sp,
                        textAlign = TextAlign.Right,
                        fontFamily = tescoFontFamily,
                        fontWeight = FontWeight.Normal
                    )

                    if (sku.priceMatched) {
                        Text(
                            "Aldi Price Match",
                            fontSize = 10.sp,
                            fontFamily = tescoFontFamily,
                            fontWeight = FontWeight.Normal,
                            color = Color.Gray
                        )
                    }
                }
            }

            if (alternatives.isNotEmpty()) {
                Row {
                    AlternativesView(alternatives)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PromptPreview() {
    HackathonTheme {
        Prompt(
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