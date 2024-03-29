package com.techflix.weatherquick.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techflix.weatherquick.R
import com.techflix.weatherquick.presentation.WeatherState
import java.time.format.DateTimeFormatter

@Composable
fun WeatherCard(
    state: WeatherState,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    cityName: String?
) {
    state.weatherInfo?.currentWeatherData?.let { data ->

        Card(
            colors = CardDefaults.cardColors(
                containerColor = backgroundColor,
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = modifier.padding(6.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    cityName?.let { cityName ->
                        Row() {
                            Image(
                                painterResource(id = R.drawable.ic_location),
                                contentDescription = "Location Icon",
                                modifier = Modifier.height(24.dp),
                                colorFilter = ColorFilter.tint(Color.Red)
                            )
                            Text(text = cityName, color = Color.White)
                        }
                    }
                    Spacer(
                        Modifier
                            .weight(1f))

                    Text(
                        "Today " +
                                "${data.time.format(DateTimeFormatter.ofPattern("HH:mm"))}",
                        color = Color.White,
                    )
                }

                Spacer(modifier = Modifier.height(60.dp))
                Image(
                    painter = painterResource(id = data.weatherType.iconRes),
                    contentDescription = data.weatherType.weatherDesc,
                    modifier = Modifier.width(200.dp)
                )
                Text(
                    text = "${data.temperatureCelsius}°C",
                    fontSize = 50.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = data.weatherType.weatherDesc,
                    fontSize = 20.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    ExtraWeatherInfo(
                        value = data.humidity,
                        unit = "%",
                        icon = R.drawable.ic_drop,
                        textStyle = TextStyle(Color.White)
                    )
                    ExtraWeatherInfo(
                        value = data.pressure,
                        unit = "hpa",
                        icon = R.drawable.ic_pressure,
                        textStyle = TextStyle(Color.White)
                    )
                    ExtraWeatherInfo(
                        value = data.windSpeed,
                        unit = "km/h",
                        icon = R.drawable.ic_wind,
                        textStyle = TextStyle(Color.White)
                    )
                }
            }
        }
    }

}