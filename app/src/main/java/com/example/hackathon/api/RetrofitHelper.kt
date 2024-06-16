package com.example.hackathon.api

import com.example.hackathon.BuildConfig
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private const val API_URL = BuildConfig.APIURL

    fun getInstance(): GetAlternativesApi {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(GetAlternativesApi::class.java)
    }
}