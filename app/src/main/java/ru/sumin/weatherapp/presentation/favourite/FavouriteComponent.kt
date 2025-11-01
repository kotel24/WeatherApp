package ru.sumin.weatherapp.presentation.favourite

import kotlinx.coroutines.flow.StateFlow
import ru.sumin.weatherapp.domain.entity.City

interface FavouriteComponent {

    val model: StateFlow<FavouriteStore.State>

    fun onClickSearch()

    fun onClickAddFavourite()

    fun onCityItemClick(city: City)

}