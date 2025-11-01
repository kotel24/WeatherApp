package ru.sumin.weatherapp

import android.app.Application
import ru.sumin.weatherapp.di.ApplicationComponent
import ru.sumin.weatherapp.di.DaggerApplicationComponent

class WeatherApp: Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }
}