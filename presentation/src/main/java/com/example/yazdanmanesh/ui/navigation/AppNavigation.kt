package com.example.yazdanmanesh.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.yazdanmanesh.ui.utils.serializableType
import com.yazdanmanesh.common_entity.Article
import com.yazdanmanesh.common_entity.Source
import kotlin.reflect.typeOf

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Navigation.Routes.HOME
    ) {
        composable(
            route = Navigation.Routes.HOME
        ) {
            HomeScreenDestination(navController)
        }

        composable<Article>(
            typeMap = mapOf(typeOf<Source>() to serializableType<Source>())
        )
        { _ ->
            DetailsScreenDestination(navController = navController)
        }
    }
}

object Navigation {
    object Routes {
        const val HOME = "home"
    }
}

fun NavController.navigateToDetails(article: Article) {
    navigate(article)
}