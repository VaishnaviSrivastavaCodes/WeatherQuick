package com.techflix.weatherquick.presentation.composables


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.techflix.weatherquick.domain.weather.WeatherData
import java.time.format.DateTimeFormatter

@Composable
fun ForecastItemHourly(
    weatherData: WeatherData,
    modifier: Modifier = Modifier,
    textColor: Color = Color.White
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${
                weatherData.time.format
                    (DateTimeFormatter.ofPattern("HH:mm"))
            }",
            color = Color.LightGray
        )
        Image(
            painter = painterResource(id = weatherData.weatherType.iconRes),
            contentDescription = "Weather Forecast Icon",
            modifier = Modifier.width(40.dp)
        )

        Text(
            text = "${
                weatherData.temperatureCelsius
            }°C",
            color = textColor,
            fontWeight = FontWeight.Bold
        )
    }

}