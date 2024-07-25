package com.example.movieapp

sealed class HomeIntent {
    data object GetMovieList: HomeIntent()
    data object PagingTryAgain: HomeIntent()
}