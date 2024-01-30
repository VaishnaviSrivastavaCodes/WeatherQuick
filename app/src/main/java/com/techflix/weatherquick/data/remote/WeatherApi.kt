package com.techflix.weatherquick.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    companion object {
        const val LATITUDE = "latitude"
        const val LONGITUDE = "longitude"
        const val END_POINT =
            "v1/forecast?hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl"
    }

    @GET(END_POINT)
    suspend fun getWeatherData(
        @Query(LATITUDE) lat: Double,
        @Query(LONGITUDE) long: Double
    ): Response<WeatherDto>
}