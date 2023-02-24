package com.example.apuestaya.user.cases.auth

import com.example.apuestaya.model.endpoints.SportEndPoint
import com.example.apuestaya.model.entities.api.enfrentamientos.Enfrentamientos
import com.example.apuestaya.model.repositories.SportAPIRepository

class SportAuthUC {
    suspend fun getEnfrentamientos(): Enfrentamientos? {
        return try {
            val service = SportAPIRepository().buildSportService(SportEndPoint::class.java)
            val response = service.enfrentamientos("all")
            if (response.isSuccessful) {
                response.body()
            } else {
                throw Exception(response.message())
            }
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }
}