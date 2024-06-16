package com.example.hackathon.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface GetAlternativesApi {
    @Headers("Accept: application/json")
    @GET("/ways-to-save/")
    suspend fun getResponse(
        @Header("Authorization") authHeader: String,
        @Query("gtin") gtin: String,
        @Query("n") number: String?
    ): Response<ApiResponse>
}