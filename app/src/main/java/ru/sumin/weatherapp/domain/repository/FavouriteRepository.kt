package ru.sumin.weatherapp.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.sumin.weatherapp.domain.entity.City

interface FavouriteRepository {

    val favouriteCities: Flow<City>

    fun observeIsFavourite(cityId: Int): Boolean

    suspend fun addToFavourite(city: City)

    suspend fun removeFromFavourite(cityId: Int)

}