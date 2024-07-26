package com.example.movieapp.data.repositories

import com.example.movieapp.data.Result
import com.example.movieapp.data.dto.MovieDetailDto

interface MovieDetailRepository {
    fun getMovieDetail(movieId: String): Result<MovieDetailDto>
}