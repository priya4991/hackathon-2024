package com.example.hackathon.compose

import android.util.Log
import androidx.compose.runtime.Composable
import com.example.hackathon.model.ItemSku

@Composable
fun Promotions(sku: ItemSku) {
    Log.i("Promotions", "Rendering promotions")
    Log.i("Promotions", "status: " + sku.status + " & gtin: " + sku.gtin )
    if (sku.status.equals("reduced")) {
        Reduced()
    } else {
        if (sku.promotions != null && sku.promotions!!.isNotEmpty()) {

            ClubCard(promo = sku.promotions!!.get(0))

        }
    }
}