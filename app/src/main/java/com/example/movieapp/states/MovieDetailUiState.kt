package com.example.movieapp.states

import com.example.movieapp.model.MovieDetail

enum class MovieState {
    SUCCESS,
    LOADING,
    ERROR
}

data class MovieDetailUiState(
    val uiState: MovieState,
    val movieDetail: MovieDetail
)