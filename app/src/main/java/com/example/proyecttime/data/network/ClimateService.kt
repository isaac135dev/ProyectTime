package com.example.proyecttime.data.network

import DataClimate
import com.example.proyecttime.core.PronoticTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ClimateService {
    private val retrofit = PronoticTime.getRetrofit()

    private val key = "011597f91a5de97b4bf2c1ffd84e084b"

    suspend fun getClimate(lat: Double, lon: Double): DataClimate {
        return withContext(Dispatchers.IO) {
            val response =
                retrofit.create(TimeApiClient::class.java).getAllClimate(lat, lon, key, "sp", "metric")
            response.body()!!
        }
    }
}