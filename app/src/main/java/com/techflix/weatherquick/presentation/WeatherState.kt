package com.techflix.weatherquick.presentation

import com.techflix.weatherquick.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean? = false,
    val error: String? = null,
    val latitude: Double?=null,
    val longitude: Double?=null,
)
