package com.example.movieapp.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost


@Composable
fun MovieAppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = homeScreenRoute
    ) {
        homeScreen { movie ->
            navController.navigateToMovieDetail(movie)
        }
        movieDetail()
    }
}