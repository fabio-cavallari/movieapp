package com.example.movieapp.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.Result
import com.example.movieapp.data.repositories.MovieDetailRepository
import com.example.movieapp.intents.MovieDetailIntent
import com.example.movieapp.model.MovieDetail
import com.example.movieapp.states.MovieDetailUiState
import com.example.movieapp.states.MovieState
import com.example.movieapp.states.MovieState.ERROR
import com.example.movieapp.states.MovieState.LOADING
import com.example.movieapp.states.MovieState.SUCCESS
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val repository: MovieDetailRepository) : ViewModel() {
    private val _state: MutableStateFlow<MovieDetailUiState> = MutableStateFlow(
        MovieDetailUiState(
            LOADING, MovieDetail()
        )
    )
    val state: StateFlow<MovieDetailUiState> = _state

    fun onIntent(movieDetailIntent: MovieDetailIntent) {
        when (movieDetailIntent) {
            is MovieDetailIntent.GetMovieDetail -> getMovieDetail(movieDetailIntent.movieId)
        }
    }

    private fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            val movieDetailState = _state.value
            _state.value = movieDetailState.copy(uiState = LOADING)
            //TODO - remover toString
            val result = repository.getMovieDetail(movieId = movieId.toString())
            if (result is Result.Success) {
                val movieDetail = result.data
                _state.value = movieDetailState.copy(uiState = SUCCESS, movieDetail = movieDetail)
            } else {
                _state.value = movieDetailState.copy(uiState = ERROR)
            }
        }
    }
}