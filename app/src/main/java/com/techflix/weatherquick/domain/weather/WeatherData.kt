package com.techflix.weatherquick.domain.weather

import java.time.LocalDateTime

data class WeatherData(
    val time: LocalDateTime,
    val temperatureCelsius: Double,
    val pressure: Double,
    val windSpeed: Double,
    val humidity: Double,
    val weatherType: WeatherType
)


data class WeatherInfo(
    val weatherDataPerDay: Map<Int, List<WeatherData>>, //maps the current day index to hourly weather data for that day
    val currentWeatherData: WeatherData? //weather for the current day and current hour
)


data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)