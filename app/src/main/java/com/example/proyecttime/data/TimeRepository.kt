package com.example.proyecttime.data

import DataClimate
import com.example.proyecttime.data.network.ClimateService

class TimeRepository {

    var api = ClimateService()

    suspend fun getAllClimateFromApi(lat: Double, lon: Double): DataClimate {
        return api.getClimate(lat, lon)
    }
}