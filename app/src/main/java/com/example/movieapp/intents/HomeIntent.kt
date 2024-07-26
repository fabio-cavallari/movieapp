package com.example.movieapp.intents

import com.example.movieapp.model.Movie

sealed class HomeIntent {
    data object GetMovieList: HomeIntent()
    data object PagingTryAgain: HomeIntent()
    data class GoToMovieDetail(val movie: Movie) : HomeIntent()
}