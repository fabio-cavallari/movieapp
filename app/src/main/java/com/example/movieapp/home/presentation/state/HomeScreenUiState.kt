package com.example.movieapp.home.presentation.state

import com.example.movieapp.home.domain.Movie

enum class HomeState {
    LOADING,
    SUCCESS,
    ERROR,
    PAGING_ERROR,
    PAGING
}

data class HomeScreenUiState(
    val page: Int = 0,
    val movieList: LinkedHashSet<Movie> = linkedSetOf(),
    val uiState: HomeState = HomeState.LOADING
) {

    fun getErrorState(): HomeState {
        return if (page > 1) {
            HomeState.PAGING_ERROR
        } else {
            HomeState.ERROR
        }
    }
}