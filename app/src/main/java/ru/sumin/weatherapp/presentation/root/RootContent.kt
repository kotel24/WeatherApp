package ru.sumin.weatherapp.presentation.root

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import ru.sumin.weatherapp.presentation.details.DetailsContent
import ru.sumin.weatherapp.presentation.favourite.FavouriteContent
import ru.sumin.weatherapp.presentation.search.SearchContent
import ru.sumin.weatherapp.presentation.ui.theme.WeatherAppTheme

@Composable
fun RootContent(component: RootComponent) {
    WeatherAppTheme {
        Children(stack = component.stack) {
            when(val instance = it.instance){
                is RootComponent.Child.Details -> DetailsContent(component = instance.component)
                is RootComponent.Child.Favourite -> FavouriteContent(component = instance.component)
                is RootComponent.Child.Search -> SearchContent(component = instance.component)
            }
        }
    }
}