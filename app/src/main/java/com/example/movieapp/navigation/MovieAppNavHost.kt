package com.example.movieapp.navigation


import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.movieapp.screens.HomeScreen
import com.example.movieapp.screens.MovieDetailScreen

internal const val homeScreenRoute = "home"
internal const val baseMovieDetailRoute = "detail/"
internal const val movieDetailRoute = "detail/{movieId}"

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MovieAppNavHost(navController: NavHostController) {
    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = homeScreenRoute
        ) {
            composable(
                route = homeScreenRoute
            ) {
                HomeScreen(navHostController = navController, animatedVisibilityScope = this)
            }
            composable(
                route = movieDetailRoute,
                arguments = listOf(navArgument("movieId") { type = NavType.IntType })
            ) { backStackEntry ->
                MovieDetailScreen(navController, this)
            }
        }
    }
}

fun NavController.navigateToMovieDetail(movieId: Int) {
    navigate("$baseMovieDetailRoute$movieId")
}