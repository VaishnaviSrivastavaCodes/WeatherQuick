package com.techflix.weatherquick.data.remote

import com.google.gson.annotations.SerializedName

data class WeatherDataDto(
    @SerializedName("time")
    val time: List<String>,
    @SerializedName("temperature_2m")
    val temperatures: List<Double>,
    @SerializedName("weathercode")
    val weatherCodes: List<Int>,
    @SerializedName("relativehumidity_2m")
    val humidities: List<Double>,
    @SerializedName("windspeed_10m")
    val windSpeeds: List<Double>,
    @SerializedName("pressure_msl")
    val pressures: List<Double>
)

data class WeatherDto(
    @SerializedName("hourly")
    val weatherResponse : WeatherDataDto
)