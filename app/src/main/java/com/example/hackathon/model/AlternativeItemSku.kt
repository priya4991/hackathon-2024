package com.example.hackathon.model

class AlternativeItemSku(
    id: String,
    title: String,
    brandname: String,
    gtin: String,
    defaultimageurl: String,
    aisleid: String?,
    aislename: String?,
    shelfid: String?,
    shelfname: String?,
    price: String?,
    unitprice: String?,
    unitofmeasure: String?,
    status: String?,
    promotions: List<Promotion>?,
    var goodnessIndex: Double,
): ItemSku(id, title, brandname, gtin, defaultimageurl, aisleid, aislename, shelfid, shelfname, price,
    unitprice, unitofmeasure, status, promotions)