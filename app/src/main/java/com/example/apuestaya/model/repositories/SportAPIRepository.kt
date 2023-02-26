package com.example.apuestaya.model.repositories

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SportAPIRepository {
    private fun getRetrofitBuilder(base: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(base)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    public fun <T> buildFootballService(service: Class<T>): T {
        val builder = getRetrofitBuilder("https://v3.football.api-sports.io/")
        return builder.create(service)
    }

    public fun <T> buildBasketballService(service: Class<T>): T {
        val builder = getRetrofitBuilder("https://v1.basketball.api-sports.io/")
        return builder.create(service)
    }
}