package com.example.movieapp.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.utils.Result
import com.example.movieapp.data.repositories.MovieDetailRepository
import com.example.movieapp.intents.MovieDetailIntent
import com.example.movieapp.model.MovieDetail
import com.example.movieapp.states.MovieDetailUiState
import com.example.movieapp.states.MovieState.ERROR
import com.example.movieapp.states.MovieState.LOADING
import com.example.movieapp.states.MovieState.SUCCESS
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val repository: MovieDetailRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state: MutableStateFlow<MovieDetailUiState> = MutableStateFlow(
        MovieDetailUiState(
            LOADING, MovieDetail()
        )
    )
    val state: StateFlow<MovieDetailUiState> = _state

    private var movieId: Int? = null

    init {
        movieId = savedStateHandle["movieId"]
        movieId?.run { getMovieDetail() }
    }

    fun onIntent(movieDetailIntent: MovieDetailIntent) {
        when (movieDetailIntent) {
            is MovieDetailIntent.GetMovieDetail -> getMovieDetail()
            else -> {}
        }
    }

    private fun getMovieDetail() {
        viewModelScope.launch {
            val movieDetailState = _state.value
            _state.value = movieDetailState.copy(uiState = LOADING)
            val result = movieId?.let { repository.getMovieDetail(it) }
            if (result is Result.Success) {
                val movieDetail = result.data
                _state.value = movieDetailState.copy(uiState = SUCCESS, movieDetail = movieDetail)
            } else {
                _state.value = movieDetailState.copy(uiState = ERROR)
            }
        }
    }
}