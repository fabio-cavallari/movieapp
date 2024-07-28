package com.example.movieapp.intents

import com.example.movieapp.model.Movie

sealed class MovieDetailIntent {
    data class GetMovieDetail(val movieId: Int): MovieDetailIntent()
}