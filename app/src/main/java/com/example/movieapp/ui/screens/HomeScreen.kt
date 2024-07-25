package com.example.movieapp.ui.screens

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
import com.example.movieapp.model.Movie
import com.example.movieapp.model.movieListSample
import com.example.movieapp.ui.components.MovieCard
import com.example.movieapp.ui.states.UiState
import com.example.movieapp.viewmodels.MovieListViewModel

@Composable
fun HomeScreen(viewModel: MovieListViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    HomeScreen(uiState.uiState, uiState.movieList, viewModel::getMovieList, viewModel::onPagingTryAgainClick)
}

@Composable
fun HomeScreen(
    uiState: UiState = UiState.SUCCESS,
    movieList: LinkedHashSet<Movie> = linkedSetOf(),
    getMovieList: () -> Unit = {},
    onPagingTryAgain: () -> Unit = {}
) {
    when (uiState) {
        UiState.ERROR -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = getMovieList
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
                    items = movieList.toList(),
                    key = { it.id },
                ) { movie ->
                    MovieCard(movie)
                }
                item {
                    when(uiState) {
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
                                    getMovieList()
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
                                    onClick = onPagingTryAgain
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