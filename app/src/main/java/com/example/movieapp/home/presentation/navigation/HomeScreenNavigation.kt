package com.example.movieapp.home.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.movieapp.home.presentation.screen.HomeScreen


internal const val homeScreenRoute = "home"

fun NavGraphBuilder.homeScreen(
    navController: NavHostController
) {
    composable(
        route = homeScreenRoute
    ) {
        HomeScreen(navHostController = navController)
    }
}