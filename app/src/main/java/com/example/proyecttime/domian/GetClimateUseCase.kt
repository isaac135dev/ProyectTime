package com.example.proyecttime.domian

import DataClimate
import com.example.proyecttime.data.TimeRepository

class GetClimateUseCase {
    val repository = TimeRepository()

    suspend operator fun invoke(lat: Double, lon: Double): DataClimate {
        return repository.getAllClimateFromApi(lat, lon)
    }
}