package com.example.movieapp.ui.states

import com.example.movieapp.model.Movie


enum class UiState {
    LOADING,
    SUCCESS,
    ERROR,
    PAGING_ERROR,
    PAGING_LOADING
}

data class HomeScreenUiState(
    val page: Int = 0,
    val movieList: List<Movie> = listOf(),
    val uiState: UiState = UiState.LOADING
) {
    fun getLoadingState(): UiState {
        return if (page > 1) {
            UiState.PAGING_LOADING
        } else {
            UiState.LOADING
        }
    }

    fun getErrorState(): UiState {
        return if (page > 1) {
            UiState.PAGING_ERROR
        } else {
            UiState.ERROR
        }
    }
}