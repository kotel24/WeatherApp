package ru.sumin.weatherapp.data.mapper

import ru.sumin.weatherapp.data.network.dto.WeatherCurrentDto
import ru.sumin.weatherapp.data.network.dto.WeatherDto
import ru.sumin.weatherapp.data.network.dto.WeatherForecastDto
import ru.sumin.weatherapp.domain.entity.Forecast
import ru.sumin.weatherapp.domain.entity.Weather
import java.util.Calendar
import java.util.Date

fun WeatherCurrentDto.toEntity(): Weather = current.toEntity()

fun WeatherDto.toEntity(): Weather = Weather(
    tempC = tempC,
    conditionText = conditionDto.text,
    conditionUrl = conditionDto.icon.correctImageUrl(),
    date = date.toCalendar()
)

fun WeatherForecastDto.toEntity(): Forecast = Forecast(
    currentWeather = currentDto.toEntity(),
    upcoming = forecastDto.forecastDay.drop(1).map{
        val dayWeatherDto = it.dayWeatherDto
        Weather(
            tempC = dayWeatherDto.tempC,
            conditionText = dayWeatherDto.conditionDto.text,
            conditionUrl = dayWeatherDto.conditionDto.icon.correctImageUrl(),
            date = it.date.toCalendar()
        )
    }
)

private fun Long.toCalendar() = Calendar.getInstance().apply {
    time = Date(this@toCalendar*1000)
}

private fun String.correctImageUrl() = "https:$this".replace(oldValue = "64x64", newValue = "128x128")