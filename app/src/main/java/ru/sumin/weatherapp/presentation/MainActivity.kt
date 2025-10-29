package ru.sumin.weatherapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import ru.sumin.weatherapp.data.network.api.ApiFactory
import ru.sumin.weatherapp.presentation.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val apiService = ApiFactory.apiService

        CoroutineScope(Dispatchers.Main).launch {
            val currentWeather = apiService.loadCurrentWeather("London")
            val currentForecast = apiService.loadForecast("London")
            val search= apiService.searchCity("London")
            Log.d("MainActivity", "currentWeather: $currentWeather\ncurrentForecast: $currentForecast\nsearch: $search ")
        }
        setContent {
            WeatherAppTheme {

            }
        }
    }
}