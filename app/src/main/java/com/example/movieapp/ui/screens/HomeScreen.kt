package com.example.movieapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.model.Movie
import com.example.movieapp.model.movieListSample
import com.example.movieapp.ui.components.MovieCard
import com.example.movieapp.ui.states.UiState
import com.example.movieapp.viewmodels.MovieListViewModel

@Composable
fun HomeScreen(viewModel: MovieListViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    HomeScreen(uiState.uiState,  uiState.movieList)
    
}
@Composable
fun HomeScreen(uiState: UiState = UiState.SUCCESS, movieList: List<Movie> = listOf()) {
    when (uiState) {
        UiState.ERROR -> {
            Text(text = "error case")
        }
        UiState.LOADING -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(64.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
        else -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(8.dp)

            ) {
                items(movieList) { movie ->
                    MovieCard(movie)
                }
            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenSuccessPreview() {
    HomeScreen(movieList = movieListSample)
}
@Preview(showSystemUi = true)
@Composable
private fun HomeScreenLoadingPreview() {
    HomeScreen(UiState.LOADING)
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenErrorPreview() {
    HomeScreen(UiState.ERROR)
}