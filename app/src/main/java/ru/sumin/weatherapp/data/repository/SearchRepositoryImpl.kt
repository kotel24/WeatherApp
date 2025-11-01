package ru.sumin.weatherapp.data.repository

import ru.sumin.weatherapp.data.mapper.toEntity
import ru.sumin.weatherapp.data.network.api.ApiService
import ru.sumin.weatherapp.domain.entity.City
import ru.sumin.weatherapp.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : SearchRepository {
    override suspend fun search(query: String): List<City> =
        apiService.searchCity(query).map { it.toEntity() }
}