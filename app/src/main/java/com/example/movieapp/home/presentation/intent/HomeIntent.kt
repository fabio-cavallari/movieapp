package com.example.movieapp.home.presentation.intent

import com.example.movieapp.home.domain.Movie

sealed class HomeIntent {
    data object GetMovieList: HomeIntent()
    data object PagingTryAgain: HomeIntent()
    data class GoToMovieDetail(val movie: Movie) : HomeIntent()
}