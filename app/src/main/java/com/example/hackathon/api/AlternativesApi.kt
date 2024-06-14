package com.example.hackathon.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import java.util.UUID

interface AlternativesApi {
    @Headers("Accept: application/json")
//    @GET("/sku/{id}")
    @GET("/random_joke/")
    suspend fun getAlternatives(): Response<AlternativeItem>?
//    suspend fun getAlternatives(@Path("id") skuId: UUID): Response<Alternatives>
}