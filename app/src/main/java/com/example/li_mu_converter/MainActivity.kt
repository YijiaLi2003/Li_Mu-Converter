package com.example.li_mu_converter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.li_mu_converter.ui.theme.Li_MuConverterTheme
import androidx.compose.ui.tooling.preview.Preview
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Li_MuConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Converter(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Converter(modifier: Modifier = Modifier) {
    var celsius by remember { mutableStateOf(0f) }
    var fahrenheit by remember { mutableStateOf(32f) }

    var fahrenheitSliderPosition by remember { mutableStateOf(32f) }

    Column(modifier = modifier.padding(16.dp)) {
        Text(text = "Celsius: ${celsius.roundToInt()}ºC")
        Slider(
            value = celsius,
            onValueChange = { newCelsius ->
                celsius = newCelsius
                fahrenheit = celsius * 9f / 5f + 32f
                fahrenheitSliderPosition = fahrenheit
            },
            valueRange = 0f..100f
        )

        Text(text = "Fahrenheit: ${fahrenheitSliderPosition.roundToInt()}ºF")
        Slider(
            value = fahrenheitSliderPosition,
            onValueChange = { newFahrenheit ->
                fahrenheitSliderPosition = newFahrenheit
                fahrenheit = newFahrenheit

                if (newFahrenheit >= 32f) {
                    celsius = (newFahrenheit - 32f) * 5f / 9f
                } else {
                    celsius = 0f
                }
            },
            onValueChangeFinished = {
                if (fahrenheit < 32f) {
                    fahrenheit = 32f
                    fahrenheitSliderPosition = 32f
                }
            },
            valueRange = 0f..212f
        )

        val message = if (celsius <= 20f) {
            "I wish it were warmer."
        } else {
            "I wish it were colder."
        }

        Text(text = message)
    }
}

@Preview(showBackground = true)
@Composable
fun ConverterPreview() {
    Li_MuConverterTheme {
        Converter()
    }
}
