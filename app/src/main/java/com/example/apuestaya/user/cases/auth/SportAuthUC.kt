package com.example.apuestaya.user.cases.auth

import com.example.apuestaya.model.endpoints.SportEndPoint
import com.example.apuestaya.model.entities.api.enfrentamientos.Enfrentamientos
import com.example.apuestaya.model.repositories.SportAPIRepository

class SportAuthUC {
    suspend fun getEnfrentamientos(league: Int, season: Int, status: String): Enfrentamientos? {
        return try {
            val service = SportAPIRepository().buildSportService(SportEndPoint::class.java)
            val response = service.enfrentamientos(league, season, status)
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