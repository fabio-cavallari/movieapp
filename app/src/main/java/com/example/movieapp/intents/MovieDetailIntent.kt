package com.example.movieapp.intents

import com.example.movieapp.model.Movie

sealed class MovieDetailIntent {
    data object GoBack: MovieDetailIntent()
    data object GetMovieDetail: MovieDetailIntent()
}