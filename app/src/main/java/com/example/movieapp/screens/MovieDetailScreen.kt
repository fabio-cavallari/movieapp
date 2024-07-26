package com.example.movieapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController

@Composable
fun MovieDetailScreen(navController: NavHostController, movieId: String) {
    Box(
        Modifier
            .fillMaxSize()
            .background(color = Color.Red)
    ) {

    }
}