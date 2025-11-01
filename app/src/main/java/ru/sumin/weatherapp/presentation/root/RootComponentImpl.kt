package ru.sumin.weatherapp.presentation.root

import android.os.Parcelable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
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

    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = Config.Favourite,
        handleBackButton = true,
        childFactory = ::child
    )

    private fun child(
        config: Config,
        componentContext: ComponentContext
    ): RootComponent.Child{
        return when(config){
            is Config.Details -> {
                val component = detailsComponentFactory.create(
                    city = config.city,
                    onBackClicked = {
                        navigation.pop()
                    },
                    componentContext = componentContext
                )
                RootComponent.Child.Details(component)
            }
            Config.Favourite -> {
                val component = favouriteComponentFactory.create(
                    onCityItemClicked = {
                        navigation.push(Config.Details(it))
                    },
                    onAddFavouriteClicked = {
                        navigation.push(Config.Search(OpenReason.AddToFavourite))
                    },
                    onSearchClicked = {
                        navigation.push(Config.Search(OpenReason.RegularSearch))
                    },
                    componentContext = componentContext
                )
                RootComponent.Child.Favourite(component)
            }
            is Config.Search -> {
                val component = searchComponentFactory.create(
                    openReason = config.openReason,
                    onCityClicked = {
                        navigation.push(Config.Details(it))
                    },
                    onBackClicked = {
                        navigation.pop()
                    },
                    onSavedToFavourite = {
                        navigation.pop()
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