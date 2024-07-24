package com.example.movieapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.Result
import com.example.movieapp.data.repositories.MovieListRepository
import com.example.movieapp.data.repositories.MovieRepositoryImpl
import com.example.movieapp.ui.states.HomeScreenUiState
import com.example.movieapp.ui.states.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieListViewModel: ViewModel() {
    val repository: MovieListRepository = MovieRepositoryImpl()

    private val _uiState: MutableStateFlow<HomeScreenUiState> = MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState> = _uiState

    suspend fun getMovieList(page: Int) {
        viewModelScope.launch {
            val homeScreenUiState = _uiState.value
            _uiState.value = homeScreenUiState.copy(uiState = homeScreenUiState.getLoadingState())
            val result = repository.getMovieList(page)
            if (result is Result.Success) {
                val movieResponse = result.data
                _uiState.value = HomeScreenUiState(
                    page = movieResponse.page,
                    movieList = movieResponse.results,
                    uiState = UiState.SUCCESS
                )
            } else {
                _uiState.value = homeScreenUiState.copy(uiState = homeScreenUiState.getErrorState())
            }
        }
    }

}