package com.example.movieapp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.movieapp.screens.MovieDetailScreen

internal const val movieDetailRoute = "movieDetail"

fun NavGraphBuilder.movieDetail(navController: NavHostController) {
    composable(
        route = movieDetailRoute
    ) { backStackEntry ->
        val movieId = backStackEntry.arguments?.getString("movieId") ?: ""
        MovieDetailScreen(navController, movieId)
    }
}

fun NavController.navigateToMovieDetail(movieId: String) {
    navigate(movieDetailRoute)
}