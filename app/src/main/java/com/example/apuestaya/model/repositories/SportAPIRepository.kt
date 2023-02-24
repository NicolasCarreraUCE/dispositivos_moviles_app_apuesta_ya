package com.example.apuestaya.model.repositories

import com.example.apuestaya.model.endpoints.SportEndPoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SportAPIRepository {
    private fun getRetrofitBuilder(base: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(base)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    public fun <T> buildSportService(service: Class<T>): T {
        val builder = getRetrofitBuilder("https://v3.football.api-sports.io/")
        return builder.create(service)
    }
}