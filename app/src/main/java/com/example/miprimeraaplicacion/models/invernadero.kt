package com.example.miprimeraaplicacion.models

data class Invernadero(
    var id: String = "",
    var sector: String = "",
    var humedad: Double = 0.0,
    var temperatura: Double = 0.0,
    var estadoVentilador: String = ""
)