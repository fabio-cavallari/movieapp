package com.example.movieapp.moviedetail.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.movieapp.moviedetail.presentation.screen.MovieDetailScreen

internal const val baseMovieDetailRoute = "detail/"
internal const val movieDetailRoute = "detail/{movieId}"

fun NavGraphBuilder.movieDetail(navController: NavHostController) {
    composable(
        route = movieDetailRoute,
        arguments = listOf(navArgument("movieId") { type = NavType.IntType })
    ) { backStackEntry ->
        MovieDetailScreen(navController)
    }
}

fun NavController.navigateToMovieDetail(movieId: Int) {
    navigate("$baseMovieDetailRoute$movieId")
}