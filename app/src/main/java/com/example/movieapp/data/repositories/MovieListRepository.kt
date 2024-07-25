package com.example.movieapp.data.repositories

import com.example.movieapp.data.Result
import com.example.movieapp.model.MovieResponse

interface MovieListRepository {
    suspend fun getMovieList(page: Int): Result<MovieResponse>
}