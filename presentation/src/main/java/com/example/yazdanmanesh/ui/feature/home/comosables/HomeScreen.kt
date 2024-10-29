package com.example.yazdanmanesh.ui.feature.home.comosables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import com.example.yazdanmanesh.ui.base.SIDE_EFFECTS_KEY
import com.example.yazdanmanesh.ui.feature.common.NetworkError
import com.example.yazdanmanesh.ui.feature.common.Progress
import com.example.yazdanmanesh.ui.feature.home.HomeContract
import com.yazdanmanesh.common_entity.generateFakeArticles
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun HomeScreen(
    state: HomeContract.HomeState,
    effectFlow: Flow<HomeContract.Effect>?,
    onEventSent: (event: HomeContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: HomeContract.Effect.Navigation) -> Unit
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is HomeContract.Effect.Navigation.ToDetails -> onNavigationRequested(effect)
            }
        }?.collect()
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { HomeTopBar() }
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            when (state) {
                is HomeContract.HomeState.DataLoaded -> {
                    HomeListItems(
                        articles = state.articles,
                        onAddArticleClicked = {
                            onEventSent(HomeContract.Event.OnArticleClicked(it))
                        }
                    )
                }

                is HomeContract.HomeState.Error -> {
                    NetworkError { onEventSent(HomeContract.Event.Retry) }
                }

                is HomeContract.HomeState.Initial -> {
                    Progress(Modifier.fillMaxSize())
                }

                is HomeContract.HomeState.LoadingState -> {
                    Progress(Modifier.fillMaxSize())
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UsersScreenSuccessPreview() {
    HomeScreen(
        state = HomeContract.HomeState.DataLoaded(
            articles = MutableStateFlow(PagingData.from(generateFakeArticles(5))),
        ),
        effectFlow = null,
        onEventSent = {},
        onNavigationRequested = {},
    )
}

@Preview(showBackground = true)
@Composable
fun UsersScreenErrorPreview() {
    HomeScreen(
        state = HomeContract.HomeState.Initial,
        effectFlow = null,
        onEventSent = {},
        onNavigationRequested = {},
    )
}
