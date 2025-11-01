package ru.sumin.weatherapp.presentation.favourite

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.sumin.weatherapp.domain.entity.City
import ru.sumin.weatherapp.presentation.extesions.componentScope
import javax.inject.Inject

class FavouriteComponentImpl @Inject constructor(
    private val favouriteStoreFactory: FavouriteStoreFactory,
    private val onCityItemClicked: (City) -> Unit,
    private val onAddFavouriteClicked: () -> Unit,
    private val onSearchClicked: () -> Unit,
    componentContext: ComponentContext
) : FavouriteComponent, ComponentContext by componentContext{

    private val store = instanceKeeper.getStore {  favouriteStoreFactory.create() }
    private val scope = componentScope()

    init {
        componentScope().launch {
            store.labels.collect {
                when(it){
                    is FavouriteStore.Label.CityItemClick -> onCityItemClicked(it.city)
                    FavouriteStore.Label.ClickAddFavourite -> onAddFavouriteClicked()
                    FavouriteStore.Label.ClickSearch -> onSearchClicked()
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<FavouriteStore.State>
        get() = store.stateFlow

    override fun onClickSearch() {
        store.accept(FavouriteStore.Intent.ClickSearch)
    }

    override fun onClickAddFavourite() {
        store.accept(FavouriteStore.Intent.ClickAddFavourite)
    }

    override fun onCityItemClick(city: City) {
        store.accept(FavouriteStore.Intent.CityItemClick(city))
    }

}