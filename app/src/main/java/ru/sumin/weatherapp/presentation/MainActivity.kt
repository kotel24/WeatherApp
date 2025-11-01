package ru.sumin.weatherapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import ru.sumin.weatherapp.WeatherApp
import ru.sumin.weatherapp.domain.usecase.ChangeFavouriteStateUseCase
import ru.sumin.weatherapp.domain.usecase.SearchCitiesUseCase
import ru.sumin.weatherapp.presentation.root.RootComponentImpl
import ru.sumin.weatherapp.presentation.root.RootContent
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var rootComponentFactory: RootComponentImpl.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as WeatherApp).applicationComponent.inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RootContent(component = rootComponentFactory.create(defaultComponentContext()))
        }
    }
}