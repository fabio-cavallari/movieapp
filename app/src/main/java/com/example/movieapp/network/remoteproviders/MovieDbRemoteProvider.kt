package com.example.movieapp.network.remoteproviders

import com.example.movieapp.data.dto.MovieDetailDto
import com.example.movieapp.data.dto.MovieResponseDto
import retrofit2.Response

interface MovieDbRemoteProvider {
    suspend fun getMovieList(page: Int): Pair<Response<MovieResponseDto>?, Exception?>
    suspend fun getMovieDetail(movieId: String): Pair<Response<MovieDetailDto>?, Exception?>
}