package com.example.movieapp.viewmodels

import androidx.lifecycle.ViewModel
import com.example.movieapp.data.repositories.MovieListRepository
import com.example.movieapp.data.repositories.MovieRepositoryImpl

class MovieListViewModel: ViewModel() {
    val repository: MovieListRepository = MovieRepositoryImpl()

    suspend fun getMovieList() {}
}