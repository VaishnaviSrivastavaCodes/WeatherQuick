package com.techflix.weatherquick

import android.Manifest
import android.content.Context
import android.location.Geocoder
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.techflix.weatherquick.presentation.WeatherViewModel
import com.techflix.weatherquick.presentation.composables.WeatherCard
import com.techflix.weatherquick.presentation.composables.WeatherForecast
import com.techflix.weatherquick.presentation.ui.theme.DarkBlue
import com.techflix.weatherquick.presentation.ui.theme.DeepBlue
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            viewModel.loadWeatherInfo()
        }
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )

        setContent {
            Box {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(DarkBlue)
                ) {
                    WeatherCard(
                        state = viewModel.state,
                        backgroundColor = DeepBlue,
                        cityName = getCityNameFromLatLong(
                            this@MainActivity,
                            viewModel.state.latitude,
                            viewModel.state.longitude
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    WeatherForecast(state = viewModel.state)
                }
                if (viewModel.state.isLoading == true) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.White
                    )
                }
                viewModel.state.error?.let {
                    Text(
                        text = it,
                        color = Color.Red,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

        }

    }

}

fun getCityNameFromLatLong(context: Context, lat: Double?, long: Double?): String? {
    if (lat == null || long == null) return null
    val geocoder = Geocoder(context)
    return geocoder.getFromLocation(lat, long, 2).let {
        it?.get(0)?.let { add0 ->
            add0.locality.toString()
        } ?: it?.get(1)?.let { add1 ->
            add1.locality.toString()
        }
    }
}

