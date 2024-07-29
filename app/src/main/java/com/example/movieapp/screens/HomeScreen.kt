package com.example.movieapp.screens

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.movieapp.R
import com.example.movieapp.components.ErrorComponent
import com.example.movieapp.components.LoadingComponent
import com.example.movieapp.components.MovieCard
import com.example.movieapp.intents.HomeIntent
import com.example.movieapp.intents.HomeIntent.GoToMovieDetail
import com.example.movieapp.model.movieListSample
import com.example.movieapp.navigation.navigateToMovieDetail
import com.example.movieapp.states.HomeScreenUiState
import com.example.movieapp.states.HomeState
import com.example.movieapp.viewmodels.HomeScreenViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.HomeScreen(
    navHostController: NavHostController,
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: HomeScreenViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    HomeScreen(state = state, animatedVisibilityScope = animatedVisibilityScope) { homeIntent ->
        when (homeIntent) {
            is GoToMovieDetail -> {
                navHostController.navigateToMovieDetail(homeIntent.movie.id)
            }

            else -> viewModel.onIntent(homeIntent)
        }

    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.HomeScreen(
    state: HomeScreenUiState,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onIntent: (HomeIntent) -> Unit = {}
) {
    when (state.uiState) {
        HomeState.ERROR -> {
            ErrorComponent(
                errorMessage = stringResource(id = R.string.generic_error_message),
                buttonText = stringResource(id = R.string.generic_error_button_text),
                onButtonClick = { onIntent(HomeIntent.GetMovieList) }
            )
        }

        HomeState.LOADING -> {
            LoadingComponent()
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
                    MovieCard(movie, animatedVisibilityScope) {
                        onIntent(GoToMovieDetail(movie))
                    }
                }
                item {
                    when (state.uiState) {
                        HomeState.PAGING -> {
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

                        HomeState.PAGING_ERROR -> {
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
                                    Text(text = stringResource(id = R.string.generic_error_button_text))
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

//@Preview(showSystemUi = true)
//@Composable
//private fun HomeScreenSuccessPreview() {
//    val homeScreenUiState =
//        HomeScreenUiState(movieList = movieListSample, uiState = HomeState.SUCCESS)
//    HomeScreen(homeScreenUiState)
//}
//
//@Preview(showSystemUi = true)
//@Composable
//private fun HomeScreenLoadingPreview() {
//    val homeScreenUiState =
//        HomeScreenUiState(movieList = movieListSample, uiState = HomeState.LOADING)
//    HomeScreen(homeScreenUiState)
//}
//
//@Preview(showSystemUi = true)
//@Composable
//private fun HomeScreenErrorPreview() {
//    val homeScreenUiState =
//        HomeScreenUiState(movieList = movieListSample, uiState = HomeState.ERROR)
//    HomeScreen(homeScreenUiState)
//}