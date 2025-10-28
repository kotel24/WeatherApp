package ru.sumin.weatherapp.domain.usecase

import ru.sumin.weatherapp.domain.repository.SearchRepository
import javax.inject.Inject

class SearchCitiesUseCase @Inject constructor(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(query: String) = repository.search(query)
}