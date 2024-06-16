package com.example.hackathon.api

import com.example.hackathon.model.AlternativeItemSku
import com.example.hackathon.model.ItemSku

data class ApiResponse(
    var item: ItemSku,
    var related: List<AlternativeItemSku>
)