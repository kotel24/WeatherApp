package ru.sumin.weatherapp.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.sumin.weatherapp.domain.entity.City
import ru.sumin.weatherapp.domain.entity.Forecast
import ru.sumin.weatherapp.domain.entity.Weather

interface WeatherRepository {

    suspend fun getWeather(cityId: Int): Weather

    suspend fun getForecast(cityId: Int): Forecast

}