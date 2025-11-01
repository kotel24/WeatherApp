package ru.sumin.weatherapp.presentation.root

import android.os.Parcelable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.parcelize.Parcelize
import ru.sumin.weatherapp.domain.entity.City
import ru.sumin.weatherapp.presentation.details.DetailsComponentImpl
import ru.sumin.weatherapp.presentation.favourite.FavouriteComponentImpl
import ru.sumin.weatherapp.presentation.search.OpenReason
import ru.sumin.weatherapp.presentation.search.SearchComponentImpl

class RootComponentImpl @AssistedInject constructor(
    private val detailsComponentFactory: DetailsComponentImpl.Factory,
    private val searchComponentFactory: SearchComponentImpl.Factory,
    private val favouriteComponentFactory: FavouriteComponentImpl.Factory,
    @Assisted("componentContext") componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {
    override val stack: Value<ChildStack<*, RootComponent.Child>>
        get() = TODO("Not yet implemented")

    private fun child(
        config: Config,
        componentContext: ComponentContext
    ): RootComponent.Child{
        return when(config){
            is Config.Details -> {
                val component = detailsComponentFactory.create(
                    city = config.city,
                    onBackClicked = {

                    },
                    componentContext = componentContext
                )
                RootComponent.Child.Details(component)
            }
            Config.Favourite -> {
                val component = favouriteComponentFactory.create(
                    onCityItemClicked = {

                    },
                    onAddFavouriteClicked = {

                    },
                    onSearchClicked = {

                    },
                    componentContext = componentContext
                )
                RootComponent.Child.Favourite(component)
            }
            is Config.Search -> {
                val component = searchComponentFactory.create(
                    openReason = config.openReason,
                    onCityClicked = {

                    },
                    onBackClicked = {

                    },
                    onSavedToFavourite = {

                    },
                    componentContext = componentContext
                )
                RootComponent.Child.Search(component)
            }
        }
    }

    sealed interface Config: Parcelable {

        @Parcelize
        data object Favourite: Config

        @Parcelize
        data class Details(val city: City): Config

        @Parcelize
        data class Search(val openReason: OpenReason): Config
    }
    @AssistedFactory
    interface Factory{
        fun create(
            @Assisted("componentContext") componentContext: ComponentContext
        ): RootComponentImpl
    }
}