package com.example.movieapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.movieapp.R
import com.example.movieapp.components.ErrorComponent
import com.example.movieapp.components.LoadingComponent
import com.example.movieapp.intents.MovieDetailIntent
import com.example.movieapp.states.MovieDetailUiState
import com.example.movieapp.states.MovieState
import com.example.movieapp.viewmodels.MovieDetailViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieDetailScreen(
    navController: NavHostController,
    viewModel: MovieDetailViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    MovieDetailScreen(state) { movieDetailIntent ->
        when (movieDetailIntent) {
            is MovieDetailIntent.GoBack -> navController.popBackStack()
            else -> viewModel.onIntent(movieDetailIntent)
        }
    }
}

@Composable
fun MovieDetailScreen(
    state: MovieDetailUiState,
    onIntent: (MovieDetailIntent) -> Unit = {}
) {
    when (state.uiState) {
        MovieState.SUCCESS -> {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Cyan))
        }

        MovieState.LOADING -> LoadingComponent()
        MovieState.ERROR -> ErrorComponent(
            errorMessage = stringResource(id = R.string.generic_error_message),
            buttonText = stringResource(
                id = R.string.generic_error_button_text
            ),
            onButtonClick = { onIntent(MovieDetailIntent.GetMovieDetail) }
        )
    }
}