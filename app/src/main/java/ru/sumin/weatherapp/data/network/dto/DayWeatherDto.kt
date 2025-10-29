package ru.sumin.weatherapp.data.network.dto

import com.google.gson.annotations.SerializedName

class DayWeatherDto (
    @SerializedName("avgtemp_c") val tempC: Float,
    @SerializedName("condition") val conditionDto: ConditionDto
)