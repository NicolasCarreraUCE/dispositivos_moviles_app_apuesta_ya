package com.example.apuestaya.model.entities.api.enfrentamientos

data class Enfrentamientos(
    val errors: List<Any>,
    val `get`: String,
    val paging: Paging,
    val parameters: Parameters,
    val response: List<Response>,
    val results: Int
)
