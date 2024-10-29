package com.example.yazdanmanesh.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.yazdanmanesh.ui.feature.home.HomeContract
import com.example.yazdanmanesh.ui.feature.home.HomeViewModel
import com.example.yazdanmanesh.ui.feature.home.comosables.HomeScreen

@Composable
fun HomeScreenDestination(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    HomeScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is HomeContract.Effect.Navigation.ToDetails) {
                navController.navigateToDetails(navigationEffect.article)
            }
        }
    )
}
