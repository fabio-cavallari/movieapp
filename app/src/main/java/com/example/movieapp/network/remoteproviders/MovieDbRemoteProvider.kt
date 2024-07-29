package com.example.movieapp.network.remoteproviders

import com.example.movieapp.moviedetail.data.model.MovieDetailDto
import com.example.movieapp.home.data.model.MovieResponseDto
import retrofit2.Response

interface MovieDbRemoteProvider {
    suspend fun getMovieList(page: Int): Pair<Response<MovieResponseDto>?, Exception?>
    suspend fun getMovieDetail(movieId: Int): Pair<Response<MovieDetailDto>?, Exception?>
}