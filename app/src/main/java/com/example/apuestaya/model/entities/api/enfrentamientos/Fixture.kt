package com.example.apuestaya.model.entities.api.enfrentamientos

data class Fixture(
    val date: String,
    val id: Int,
    val periods: Periods,
    val referee: Any,
    val status: Status,
    val timestamp: Int,
    val timezone: String,
    val venue: Venue
)
