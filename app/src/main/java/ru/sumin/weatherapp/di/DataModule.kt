package ru.sumin.weatherapp.di

import android.content.Context
import androidx.room.Database
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.sumin.weatherapp.data.local.db.FavouriteCitiesDao
import ru.sumin.weatherapp.data.local.db.FavouriteDatabase
import ru.sumin.weatherapp.data.network.api.ApiFactory
import ru.sumin.weatherapp.data.network.api.ApiService
import ru.sumin.weatherapp.data.repository.FavouriteRepositoryImpl
import ru.sumin.weatherapp.data.repository.SearchRepositoryImpl
import ru.sumin.weatherapp.data.repository.WeatherRepositoryImpl
import ru.sumin.weatherapp.domain.repository.FavouriteRepository
import ru.sumin.weatherapp.domain.repository.SearchRepository
import ru.sumin.weatherapp.domain.repository.WeatherRepository

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindFavouriteRepository(impl: FavouriteRepositoryImpl): FavouriteRepository

    @ApplicationScope
    @Binds
    fun bindSearchRepository(impl: SearchRepositoryImpl): SearchRepository

    @ApplicationScope
    @Binds
    fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository

    companion object{
        @ApplicationScope
        @Provides
        fun provideApiService(): ApiService = ApiFactory.apiService

        @ApplicationScope
        @Provides
        fun provideFavouriteDatabase(context: Context): FavouriteDatabase =
            FavouriteDatabase.getInstance(context)

        @ApplicationScope
        @Provides
        fun provideFavouriteCitiesDao(database: FavouriteDatabase): FavouriteCitiesDao =
            database.favouriteCitiesDao()
    }

}