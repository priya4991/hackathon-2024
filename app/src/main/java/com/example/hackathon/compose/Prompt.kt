package com.example.hackathon.compose

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hackathon.api.ApiResponse
import com.example.hackathon.model.AlternativeItemSku
import com.example.hackathon.model.ItemSku
import com.example.hackathon.ui.theme.HackathonTheme
import java.util.ArrayList

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Prompt(sku: ApiResponse) {

    Box(
        modifier = Modifier
            .background(Color.White)
            .padding(all = 10.dp)
    ) {
        Column(modifier = Modifier.background(Color.White)) {

            SkuView(sku = sku.item)

//            if (sku.clubcardOffer.isNotEmpty()) {
//                Row(
//                    horizontalArrangement = Arrangement.Center,
//                    modifier = Modifier
//                        .padding(top = 10.dp, bottom = 10.dp)
//                        .fillMaxWidth()
//                ) {
//                    ClubCard(sku)
//                }
//            }

            Row {
                if (sku.related.isNotEmpty()) {
                    AlternativesList(sku.related)
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun PromptPreview() {
    val item = ItemSku(
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
    val altItem = AlternativeItemSku(
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
        emptyList(),
        0.997761785984039
    )
    val altItemList = ArrayList<AlternativeItemSku>()
    altItemList.add(altItem)
    HackathonTheme {
        Prompt(
            ApiResponse(
                "",
                item,
                altItemList

            )
        )
    }
}