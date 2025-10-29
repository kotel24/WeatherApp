package ru.sumin.weatherapp.data.network.dto

import com.google.gson.annotations.SerializedName

data class WeatherForecastDto(
    @SerializedName("current") val currentDto: WeatherDto,
    @SerializedName("forecast") val forecastDto: ForecastDto
)
