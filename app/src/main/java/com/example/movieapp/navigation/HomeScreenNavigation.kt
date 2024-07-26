package com.example.movieapp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.movieapp.screens.HomeScreen


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