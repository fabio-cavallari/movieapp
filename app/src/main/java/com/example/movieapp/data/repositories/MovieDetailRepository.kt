package com.example.movieapp.data.repositories

import com.example.movieapp.data.Result
import com.example.movieapp.model.MovieDetail

interface MovieDetailRepository {
    suspend fun getMovieDetail(movieId: Int): Result<MovieDetail>
}