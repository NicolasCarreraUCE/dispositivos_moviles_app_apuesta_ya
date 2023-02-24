package com.example.apuestaya.model.endpoints

import com.example.apuestaya.model.entities.api.enfrentamientos.Enfrentamientos
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SportEndPoint {
    @GET("fixtures")
    @Headers("x-rapidapi-host: v3.football.api-sports.io", "x-rapidapi-key: da527806b82ba8f9f78ca6a9694aeb70")
    suspend fun enfrentamientos(@Query("live") live : String) : Response<Enfrentamientos>
}
