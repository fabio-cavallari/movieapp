package com.example.movieapp.intents

sealed class HomeIntent {
    data object GetMovieList: HomeIntent()
    data object PagingTryAgain: HomeIntent()
}