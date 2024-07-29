package com.example.movieapp.shared.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.movieapp.home.presentation.navigation.homeScreen
import com.example.movieapp.home.presentation.navigation.homeScreenRoute
import com.example.movieapp.moviedetail.presentation.navigation.movieDetail


@Composable
fun MovieAppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = homeScreenRoute
    ) {
        homeScreen(navController)
        movieDetail(navController)
    }
}