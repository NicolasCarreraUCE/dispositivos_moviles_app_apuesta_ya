package com.example.apuestaya.model.entities.api.basketball

data class Games(
    val errors: List<Any>,
    val `get`: String,
    val parameters: Parameters,
    val response: List<Response>,
    val results: Int
)