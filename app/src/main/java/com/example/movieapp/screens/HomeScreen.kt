package com.example.movieapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.movieapp.components.MovieCard
import com.example.movieapp.intents.HomeIntent
import com.example.movieapp.intents.HomeIntent.GoToMovieDetail
import com.example.movieapp.model.movieListSample
import com.example.movieapp.navigation.navigateToMovieDetail
import com.example.movieapp.states.HomeScreenUiState
import com.example.movieapp.states.UiState
import com.example.movieapp.viewmodels.MovieListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(navHostController: NavHostController, viewModel: MovieListViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsState()
    HomeScreen(state) { homeIntent ->
        when (homeIntent) {
            is GoToMovieDetail -> {
                navHostController.navigateToMovieDetail(homeIntent.movie)
            }
            else -> viewModel.onIntent(homeIntent)
        }

    }
}

@Composable
fun HomeScreen(
    state: HomeScreenUiState,
    onIntent: (HomeIntent) -> Unit = {}
) {
    when (state.uiState) {
        UiState.ERROR -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = { onIntent(HomeIntent.GetMovieList) }
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "refresh"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "try again")
                }
            }
        }

        UiState.LOADING -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
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
                items(
                    items = state.movieList.toList(),
                    key = { it.id },
                ) { movie ->
                    MovieCard(movie) {
                        onIntent(GoToMovieDetail(movie))
                    }
                }
                item {
                    when (state.uiState) {
                        UiState.PAGING -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(24.dp),
                                    color = MaterialTheme.colorScheme.primary
                                )
                                LaunchedEffect(Unit) {
                                    onIntent(HomeIntent.GetMovieList)
                                }
                            }

                        }

                        UiState.PAGING_ERROR -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Button(
                                    onClick = { onIntent(HomeIntent.PagingTryAgain) }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Refresh,
                                        contentDescription = "refresh"
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(text = "try again")
                                }
                            }
                        }

                        else -> {}
                    }
                }
            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenSuccessPreview() {
    val homeScreenUiState =
        HomeScreenUiState(movieList = movieListSample, uiState = UiState.SUCCESS)
    HomeScreen(homeScreenUiState)
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenLoadingPreview() {
    val homeScreenUiState =
        HomeScreenUiState(movieList = movieListSample, uiState = UiState.LOADING)
    HomeScreen(homeScreenUiState)
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenErrorPreview() {
    val homeScreenUiState =
        HomeScreenUiState(movieList = movieListSample, uiState = UiState.ERROR)
    HomeScreen(homeScreenUiState)
}