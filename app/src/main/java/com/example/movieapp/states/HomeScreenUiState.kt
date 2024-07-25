package com.example.movieapp.states

import com.example.movieapp.model.Movie


enum class UiState {
    LOADING,
    SUCCESS,
    ERROR,
    PAGING_ERROR,
    PAGING
}

data class HomeScreenUiState(
    val page: Int = 0,
    val movieList: LinkedHashSet<Movie> = linkedSetOf(),
    val uiState: UiState = UiState.LOADING
) {

    fun getErrorState(): UiState {
        return if (page > 1) {
            UiState.PAGING_ERROR
        } else {
            UiState.ERROR
        }
    }
}