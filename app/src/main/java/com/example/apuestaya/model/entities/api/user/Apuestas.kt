package com.example.apuestaya.model.entities.api.user

data class Apuestas(
    val id:String,
    val deporte:String,
    val equipo:String,
    val estadoPartido:String,
    val estadoApuesta:String,
    val puntos:Float

)