package ru.sumin.weatherapp.presentation.search

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.sumin.weatherapp.domain.entity.City
import ru.sumin.weatherapp.presentation.extesions.componentScope

class SearchComponentImpl @AssistedInject constructor(
    private val storeFactory: SearchStoreFactory,
    @Assisted("openReason") private val openReason: OpenReason,
    @Assisted("onForecastForCityRequested") private val onForecastForCityRequested: (City) -> Unit,
    @Assisted("onBackClicked") private val onBackClicked: () -> Unit,
    @Assisted("onSavedToFavourite") private val onSavedToFavourite: () -> Unit,
    @Assisted("componentContext") componentContext: ComponentContext
) : SearchComponent, ComponentContext by componentContext{

    private val store = instanceKeeper.getStore {  storeFactory.create(openReason) }
    private val scope = componentScope()

    init {
        scope.launch {
            store.labels.collect {
                when(it){
                    SearchStore.Label.ClickBack -> onBackClicked()
                    is SearchStore.Label.OpenForecast -> onForecastForCityRequested(it.city)
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

    @AssistedFactory
    interface Factory{
        fun create(
            @Assisted("openReason") openReason: OpenReason,
            @Assisted("onForecastForCityRequested") onForecastForCityRequested: (City) -> Unit,
            @Assisted("onBackClicked") onBackClicked: () -> Unit,
            @Assisted("onSavedToFavourite") onSavedToFavourite: () -> Unit,
            @Assisted("componentContext") componentContext: ComponentContext
        ):SearchComponentImpl
    }

}