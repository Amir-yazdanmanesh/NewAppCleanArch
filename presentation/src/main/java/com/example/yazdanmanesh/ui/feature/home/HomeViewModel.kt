package com.example.yazdanmanesh.ui.feature.home

import androidx.lifecycle.viewModelScope
import com.example.yazdanmanesh.ui.base.BaseViewModel
import com.yazdanmanesh.usecase.TehranArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: TehranArticlesUseCase
) : BaseViewModel<HomeContract.Event, HomeContract.HomeState, HomeContract.Effect>() {

    init {
        getTehranArticles()
    }

    override fun setInitialState() = HomeContract.HomeState.Initial

    override fun handleEvents(event: HomeContract.Event) {
        when (event) {
            is HomeContract.Event.Retry -> getTehranArticles()
            is HomeContract.Event.OnArticleClicked -> setEffect {
                HomeContract.Effect.Navigation.ToDetails(event.article)
            }
        }
    }

    private fun getTehranArticles() {
        setState { HomeContract.HomeState.LoadingState }
        viewModelScope.launch {
            useCase.getTehranArticlesUseCase().onSuccess { data ->
                setState { HomeContract.HomeState.DataLoaded(data) }
            }.onFailure {
                setState { HomeContract.HomeState.Error }
            }
        }
    }
}
