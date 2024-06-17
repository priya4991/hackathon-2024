package com.example.hackathon.model

open class ItemSku(
    var id: String,
    var title: String,
    var brandname: String,
    var gtin: String,
    var defaultimageurl: String,
    var aisleid: String?,
    var aislename: String?,
    var shelfid: String?,
    var shelfname: String?,
    var price: String?,
    var unitprice: String?,
    var unitofmeasure: String?,
    var status: String?,
    var promotions: List<Promotion>?,
    var amount: Int = 0
)