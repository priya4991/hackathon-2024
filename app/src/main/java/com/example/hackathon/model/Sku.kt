package com.example.hackathon.model

import java.util.UUID

class Sku(
    var skuId: UUID,
    var name: String,
    var price: String,
    var pricePr: String,
    var image: Int,
    var valid: String,
    var priceMatched: Boolean,
    var clubcardOffer: List<String>
)