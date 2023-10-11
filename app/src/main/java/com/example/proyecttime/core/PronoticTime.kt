package com.example.proyecttime.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PronoticTime {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}