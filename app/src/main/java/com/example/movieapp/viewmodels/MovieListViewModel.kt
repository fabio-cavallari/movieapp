package com.example.movieapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.HomeIntent
import com.example.movieapp.HomeIntent.GetMovieList
import com.example.movieapp.HomeIntent.PagingTryAgain
import com.example.movieapp.data.Result
import com.example.movieapp.data.repositories.MovieListRepository
import com.example.movieapp.data.repositories.MovieRepositoryImpl
import com.example.movieapp.model.MovieResponse
import com.example.movieapp.states.HomeScreenUiState
import com.example.movieapp.states.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieListViewModel : ViewModel() {
    private val repository: MovieListRepository = MovieRepositoryImpl()

    private val _state: MutableStateFlow<HomeScreenUiState> =
        MutableStateFlow(HomeScreenUiState())
    val state: StateFlow<HomeScreenUiState> = _state

    init {
        getMovieList()
    }

    private fun getMovieList() {
        viewModelScope.launch {
            val homeScreenUiState = _state.value
            if (homeScreenUiState.page == 0) {
                _state.value = homeScreenUiState.copy(uiState = UiState.LOADING)
            }
            //this delay is just for giving enough time to show progress bar animations
            delay(2000)
            val result = repository.getMovieList(homeScreenUiState.page + 1)
            if (result is Result.Success) {
                val movieResponse = result.data
                val movieListUpdated = homeScreenUiState.movieList
                movieListUpdated.addAll(movieResponse.results)
                _state.value = HomeScreenUiState(
                    page = movieResponse.page,
                    movieList = movieListUpdated,
                    uiState = getUiState(movieResponse)
                )
            } else {
                _state.value = homeScreenUiState.copy(uiState = homeScreenUiState.getErrorState())
            }
        }
    }

    fun onIntent(homeIntent: HomeIntent) {
        when (homeIntent) {
            GetMovieList -> getMovieList()
            PagingTryAgain -> onPagingTryAgainClick()
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

    private fun onPagingTryAgainClick() {
        val homeScreenUiState = _state.value
        _state.value = homeScreenUiState.copy(uiState = UiState.PAGING)
    }
}