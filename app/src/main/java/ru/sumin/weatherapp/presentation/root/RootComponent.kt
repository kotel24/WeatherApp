package ru.sumin.weatherapp.presentation.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.sumin.weatherapp.presentation.details.DetailsComponent
import ru.sumin.weatherapp.presentation.favourite.FavouriteComponent
import ru.sumin.weatherapp.presentation.search.SearchComponent

interface RootComponent {

    val stack: Value<ChildStack<*, Child>>

    sealed interface Child{

        data class Favourite(val component: FavouriteComponent): Child

        data class Details(val component: DetailsComponent): Child

        data class Search(val component: SearchComponent): Child

    }
}