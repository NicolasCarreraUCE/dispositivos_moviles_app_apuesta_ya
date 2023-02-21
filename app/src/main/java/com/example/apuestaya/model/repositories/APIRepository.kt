package com.example.apuestaya.model.repositories

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIRepository {
    private fun getRetrofitBuilder(base: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(base)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    public fun <T> buildUsuariosService(service: Class<T>): T {
        val builder = getRetrofitBuilder("https://gorest.co.in/")
        return builder.create(service)
    }
}