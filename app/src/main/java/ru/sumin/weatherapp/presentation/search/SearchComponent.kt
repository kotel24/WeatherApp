package ru.sumin.weatherapp.presentation.search

import kotlinx.coroutines.flow.StateFlow
import ru.sumin.weatherapp.domain.entity.City

interface SearchComponent {

    val model: StateFlow<SearchStore.State>

    fun changeSearchQuery(query: String)

    fun onClickSearch()

    fun onClickBack()

    fun onClickCity(city: City)

}