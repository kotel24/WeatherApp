package ru.sumin.weatherapp.data.network.dto

import com.google.gson.annotations.SerializedName

data class ForecastDto(
    @SerializedName("forecast") val forecastDay: DayDto,
)
