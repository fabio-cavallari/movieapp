package com.example.movieapp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.movieapp.model.Movie
import com.example.movieapp.screens.MovieDetailScreen

internal const val movieDetailRoute = "movieDetail"

fun NavGraphBuilder.movieDetail() {
    composable(
        route = movieDetailRoute
    ) {
        MovieDetailScreen()
    }
}

fun NavController.navigateToMovieDetail(movie: Movie) {
    navigate(movieDetailRoute)
}