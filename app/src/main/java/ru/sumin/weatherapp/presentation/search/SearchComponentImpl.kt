package ru.sumin.weatherapp.presentation.search

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

class SearchComponentImpl @Inject constructor(
    private val openReason: OpenReason,
    private val storeFactory: SearchStoreFactory,
    private val onCityClicked: (City) -> Unit,
    private val onBackClicked: () -> Unit,
    private val onSavedToFavourite: () -> Unit,
    componentContext: ComponentContext
) : SearchComponent, ComponentContext by componentContext{

    private val store = instanceKeeper.getStore {  storeFactory.create(openReason) }
    private val scope = componentScope()

    init {
        scope.launch {
            store.labels.collect {
                when(it){
                    SearchStore.Label.ClickBack -> onBackClicked()
                    is SearchStore.Label.OpenForecast -> onCityClicked(it.city)
                    SearchStore.Label.SaveToFavourite -> onSavedToFavourite()
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<SearchStore.State>
        get() = store.stateFlow

    override fun changeSearchQuery(query: String) {
        store.accept(SearchStore.Intent.ChangeSearchQuery(query))
    }

    override fun onClickSearch() {
        store.accept(SearchStore.Intent.ClickSearch)
    }

    override fun onClickBack() {
        store.accept(SearchStore.Intent.ClickBack)
    }

    override fun onClickCity(city: City) {
        store.accept(SearchStore.Intent.ClickCity(city))
    }

}