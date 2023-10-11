package com.example.proyecttime.data.network

import DataClimate
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TimeApiClient {
    @GET("/data/2.5/weather")
    suspend fun getAllClimate(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String,
        @Query("lang") lang: String,
        @Query("units") units: String
    ): Response<DataClimate>
}