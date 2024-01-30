package com.techflix.weatherquick

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Image(
                painter = rememberImagePainter(data = "https://cdn2.thecatapi.com/images/6b9.jpg"),
                contentDescription = ""
            )
        }

    }

}

@Composable
fun CounterIncrease() {
    Button(onClick = { /*TODO*/ }) {
        Text(text = "Tap to increase count!")
    }
}

@Composable
fun Counter() {
    Text(text = "Count value")
}


@Composable
private fun ListItem(
    img: Int = R.drawable.ic_sunny,
    title: String = "Hello world",
    subtitle: String = "How are you ?"
) {
    Card(
        modifier = Modifier.padding(8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(img),
                contentDescription = null,
                Modifier
                    .size(40.dp)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title, fontSize = 22.sp,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(text = subtitle)
            }
        }
    }

}


@Composable
fun ShowList() {

    LazyColumn(content = {
        items(
            getItems()

        ) {
            ListItem(it.img, it.title, it.subtitle)
        }
    })
}


data class Item(val img: Int, var title: String, var subtitle: String)

fun getItems(): List<Item> {
    return mutableListOf<Item>().apply {
        add(Item(R.drawable.ic_sunny, "Hello", "Bye"))
        add(Item(R.drawable.ic_sunnycloudy, "Hello", "Bye"))
        add(Item(R.drawable.ic_drop, "Hello", "Bye"))
        add(Item(R.drawable.ic_pressure, "Hello", "Bye"))
        add(Item(R.drawable.ic_snowy, "Hello", "Bye"))
        add(Item(R.drawable.ic_rainshower, "Hello", "Bye"))
        add(Item(R.drawable.ic_snowy, "Hello", "Bye"))
        add(Item(R.drawable.ic_snowyrainy, "Hello", "Bye"))
        add(Item(R.drawable.ic_sunnyrainy, "Hello", "Bye"))
        add(Item(R.drawable.ic_thunder, "Hello", "Bye"))
        add(Item(R.drawable.ic_wind, "Hello", "Bye"))
        add(Item(R.drawable.ic_sunnyrainy, "Hello", "Bye"))
        add(Item(R.drawable.ic_sunny, "Hello", "Bye"))
        add(Item(R.drawable.ic_thunder, "Hello", "Bye"))
        add(Item(R.drawable.ic_sunnyrainy, "Hello", "Bye"))
        add(Item(R.drawable.ic_pressure, "Hello", "Bye"))
        add(Item(R.drawable.ic_windy, "Hello", "Bye"))
    }
}
