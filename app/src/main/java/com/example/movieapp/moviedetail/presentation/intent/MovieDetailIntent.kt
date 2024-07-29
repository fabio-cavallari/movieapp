package com.example.movieapp.moviedetail.presentation.intent

sealed class MovieDetailIntent {
    data object GoBack: MovieDetailIntent()
    data object GetMovieDetail: MovieDetailIntent()
}