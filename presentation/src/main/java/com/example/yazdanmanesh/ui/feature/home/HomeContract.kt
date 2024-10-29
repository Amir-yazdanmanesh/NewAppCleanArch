package com.example.yazdanmanesh.ui.feature.home

import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import com.example.yazdanmanesh.ui.base.ViewEvent
import com.example.yazdanmanesh.ui.base.ViewSideEffect
import com.example.yazdanmanesh.ui.base.ViewState
import com.yazdanmanesh.common_entity.Article
import kotlinx.coroutines.flow.Flow

class HomeContract {
    sealed class Event : ViewEvent {
        data object Retry : Event()
        data class OnArticleClicked(val article: Article) : Event()
    }

    @Immutable
    sealed class HomeState : ViewState {
        data class DataLoaded(val articles: Flow<PagingData<Article>>) : HomeState()
        data object LoadingState : HomeState()
        data object Error : HomeState()
        data object Initial : HomeState()
    }

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            data class ToDetails(val article: Article) : Navigation()
        }
    }
}
