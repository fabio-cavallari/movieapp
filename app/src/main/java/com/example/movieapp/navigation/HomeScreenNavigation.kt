package com.example.movieapp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.movieapp.model.Movie
import com.example.movieapp.screens.HomeScreen


internal const val homeScreenRoute = "home"

fun NavGraphBuilder.homeScreen(
    onNavigateToMovieDetail: (movie: Movie) -> Unit = {}
) {
    composable(
        route = homeScreenRoute
    ) {
        HomeScreen()
    }
}