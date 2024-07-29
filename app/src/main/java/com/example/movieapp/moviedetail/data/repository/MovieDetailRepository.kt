package com.example.movieapp.moviedetail.data.repository

import com.example.movieapp.shared.utils.Result
import com.example.movieapp.moviedetail.domain.MovieDetail

interface MovieDetailRepository {
    suspend fun getMovieDetail(movieId: Int): Result<MovieDetail>
}