package com.example.movieapp.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.model.movieListSample
import com.example.movieapp.ui.components.MovieCard
import com.example.movieapp.ui.states.HomeScreenUiState

@Composable
fun HomeScreen(uiState: HomeScreenUiState) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(8.dp)

    ) {
        items(uiState.movieList) { movie ->
            MovieCard(movie)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(HomeScreenUiState(movieListSample))
}