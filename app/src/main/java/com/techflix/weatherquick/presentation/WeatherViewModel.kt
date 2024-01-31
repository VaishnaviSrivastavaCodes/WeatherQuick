package com.techflix.weatherquick.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techflix.weatherquick.domain.location.LocationTracker
import com.techflix.weatherquick.domain.repository.WeatherRepository
import com.techflix.weatherquick.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
) : ViewModel() {

    var state by mutableStateOf(WeatherState())

    fun loadWeatherInfo() {
        viewModelScope.launch {
            state = state.copy(
                null, true,
            )
            locationTracker.getCurrentLocation()?.let { location ->
                when (val result =
                    repository.getWeatherData(location.latitude, location.longitude)) {
                    is Resource.Success -> {
                        state = state.copy(
                            weatherInfo = result.data,
                            isLoading = false,
                            error = null,
                            latitude = location.latitude,
                            longitude = location.longitude
                        )
                    }

                    else -> {

                        state = state.copy(
                            weatherInfo = null,
                            isLoading = false,
                            error = result.errorMessage
                        )
                    }
                }
            } ?: kotlin.run {
                state = state.copy(
                    isLoading = false,
                    error = "Couldn't receive location. Make sure that location permission is granted."
                    ,
                )
            }

        }

    }
}