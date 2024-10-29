package com.example.yazdanmanesh.ui.feature.details

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.example.yazdanmanesh.ui.base.BaseViewModel
import com.example.yazdanmanesh.ui.utils.serializableType
import com.yazdanmanesh.common_entity.Article
import com.yazdanmanesh.common_entity.Source
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.reflect.typeOf

@HiltViewModel
class DetailsViewModel @Inject constructor(savedStateHandle: SavedStateHandle) :
    BaseViewModel<DetailsContract.Event, DetailsContract.DetailsState, DetailsContract.Effect>() {
    private val typeMap = mapOf(typeOf<Source>() to serializableType<Source>())
    private val article = savedStateHandle.toRoute<Article>(typeMap)

    init {
        setState { DetailsContract.DetailsState.Initial(article) }
    }

    override fun setInitialState() = DetailsContract.DetailsState.Initial(article)

    override fun handleEvents(event: DetailsContract.Event) {
        when (event) {
            DetailsContract.Event.BackButtonClicked -> {
                setEffect { DetailsContract.Effect.Navigation.Back }
            }
        }
    }
}
