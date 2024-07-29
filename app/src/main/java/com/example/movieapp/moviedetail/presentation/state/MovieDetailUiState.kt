package com.example.movieapp.moviedetail.presentation.state

import com.example.movieapp.moviedetail.domain.MovieDetail

enum class MovieState {
    SUCCESS,
    LOADING,
    ERROR
}

data class MovieDetailUiState(
    val uiState: MovieState,
    val movieDetail: MovieDetail
)