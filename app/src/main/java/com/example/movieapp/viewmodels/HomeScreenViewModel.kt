package com.example.movieapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.Result
import com.example.movieapp.data.repositories.MovieListRepository
import com.example.movieapp.intents.HomeIntent
import com.example.movieapp.intents.HomeIntent.GetMovieList
import com.example.movieapp.intents.HomeIntent.PagingTryAgain
import com.example.movieapp.model.MovieResponse
import com.example.movieapp.states.HomeScreenUiState
import com.example.movieapp.states.HomeState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(private val repository: MovieListRepository) : ViewModel() {
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
                _state.value = homeScreenUiState.copy(uiState = HomeState.LOADING)
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
            else -> {}
        }
    }

    private fun getUiState(movieResponse: MovieResponse): HomeState {
        val isLastPage = movieResponse.totalPages == movieResponse.page
        return if (isLastPage) {
            HomeState.SUCCESS
        } else {
            HomeState.PAGING
        }

    }

    private fun onPagingTryAgainClick() {
        val homeScreenUiState = _state.value
        _state.value = homeScreenUiState.copy(uiState = HomeState.PAGING)
    }
}