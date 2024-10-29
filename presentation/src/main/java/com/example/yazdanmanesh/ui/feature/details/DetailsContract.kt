package com.example.yazdanmanesh.ui.feature.details

import com.example.yazdanmanesh.ui.base.ViewEvent
import com.example.yazdanmanesh.ui.base.ViewSideEffect
import com.example.yazdanmanesh.ui.base.ViewState
import com.yazdanmanesh.common_entity.Article

class DetailsContract {
    sealed class Event : ViewEvent {
        data object BackButtonClicked : Event()
    }

    sealed class DetailsState : ViewState {
        data class Initial(val article: Article?) : DetailsState()
    }

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            data object Back : Navigation()
        }
    }
}
