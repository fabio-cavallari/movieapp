package com.example.movieapp.home.data.repository

import com.example.movieapp.shared.utils.Result
import com.example.movieapp.home.domain.MovieResponse

interface MovieListRepository {
    suspend fun getMovieList(page: Int): Result<MovieResponse>
}