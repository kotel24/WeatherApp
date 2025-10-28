package ru.sumin.weatherapp.domain.repository

import ru.sumin.weatherapp.domain.entity.City

interface SearchRepository {

    suspend fun search(query: String): List<City>

}