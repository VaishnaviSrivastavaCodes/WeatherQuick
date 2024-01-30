package com.techflix.weatherquick.domain.repository

import com.techflix.weatherquick.domain.util.Resource
import com.techflix.weatherquick.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}