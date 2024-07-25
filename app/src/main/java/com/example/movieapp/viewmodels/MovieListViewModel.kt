package com.example.movieapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.Result
import com.example.movieapp.data.repositories.MovieListRepository
import com.example.movieapp.data.repositories.MovieRepositoryImpl
import com.example.movieapp.model.MovieResponse
import com.example.movieapp.ui.states.HomeScreenUiState
import com.example.movieapp.ui.states.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieListViewModel : ViewModel() {
    private val repository: MovieListRepository = MovieRepositoryImpl()

    private val _uiState: MutableStateFlow<HomeScreenUiState> =
        MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState> = _uiState

    init {
        getMovieList()
    }

    fun getMovieList() {
        viewModelScope.launch {
            delay(2000)
            val homeScreenUiState = _uiState.value
            if (homeScreenUiState.page == 0) {
                _uiState.value = homeScreenUiState.copy(uiState = UiState.LOADING)
            }
            val result = repository.getMovieList(homeScreenUiState.page + 1)
            if (result is Result.Success) {
                val movieResponse = result.data
                val movieListUpdated = homeScreenUiState.movieList
                movieListUpdated.addAll(movieResponse.results)
                _uiState.value = HomeScreenUiState(
                    page = movieResponse.page,
                    movieList = movieListUpdated,
                    uiState = getUiState(movieResponse)
                )
            } else {
                _uiState.value = homeScreenUiState.copy(uiState = homeScreenUiState.getErrorState())
            }
        }
    }

    private fun getUiState(movieResponse: MovieResponse): UiState {
        val isLastPage = movieResponse.totalPages == movieResponse.page
        return if (isLastPage) {
            UiState.SUCCESS
        } else {
            UiState.PAGING
        }

    }
}