package ru.sumin.weatherapp.data.network.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.sumin.weatherapp.data.network.dto.CityDto
import ru.sumin.weatherapp.data.network.dto.WeatherCurrentDto
import ru.sumin.weatherapp.data.network.dto.WeatherForecastDto

interface ApiService {
    @GET("current.json?key=f70ff2e6684142c5ab0175041252810")
    suspend fun loadCurrentWeather(
        @Query("q") query: String
    ): WeatherCurrentDto

    @GET("forecast.json?key=f70ff2e6684142c5ab0175041252810")
    suspend fun loadForecast(
        @Query("q") query: String,
        @Query("days") daysCount: Int = 4
    ): WeatherForecastDto

    @GET("search.json?key=f70ff2e6684142c5ab0175041252810")
    suspend fun searchCity(
        @Query("q") query: String
    ): List<CityDto>
}