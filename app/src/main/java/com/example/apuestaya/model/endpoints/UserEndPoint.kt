package com.example.apuestaya.model.endpoints

import com.example.apuestaya.model.entities.api.user.Usuarios
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserEndPoint {
    @GET("public/v2/users/{id}")
    suspend fun usuario(@Path("id") id : Int) : Response<Usuarios>
}