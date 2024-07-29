package com.example.movieapp.data.repositories

import com.example.movieapp.utils.Result
import com.example.movieapp.model.MovieResponse

interface MovieListRepository {
    suspend fun getMovieList(page: Int): Result<MovieResponse>
}