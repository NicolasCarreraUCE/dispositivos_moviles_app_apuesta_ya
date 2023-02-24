package com.example.apuestaya.user.cases.auth

import com.example.apuestaya.model.endpoints.UserEndPoint
import com.example.apuestaya.model.entities.api.user.Usuarios
import com.example.apuestaya.model.repositories.APIRepository

class AuthUC {
    suspend fun getUsuarioAuntentificacion(id: Int): Usuarios? {
        return try {
            val service = APIRepository().buildUsuariosService(UserEndPoint::class.java)
            val response = service.usuario(id)
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