package com.techflix.weatherquick.domain.repository

import com.techflix.weatherquick.data.mappers.toWeatherInfo
import com.techflix.weatherquick.data.remote.WeatherApi
import com.techflix.weatherquick.domain.util.Resource
import com.techflix.weatherquick.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val weatherApi: WeatherApi) :
    WeatherRepository {
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data =
                weatherApi.getWeatherData(lat, long).body()?.toWeatherInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(errorMessage = "An unknown error occurred.")
        }
    }
}