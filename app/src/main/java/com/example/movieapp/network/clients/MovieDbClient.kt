package com.example.movieapp.network.clients

import com.example.movieapp.data.dto.MovieDetailDto
import com.example.movieapp.data.dto.MovieResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDbClient {
    @GET("/3/discover/movie")
    suspend fun getMovieList(
        @Query("page") page: Int
    ): Response<MovieResponseDto>

    @GET("/3/movie/{movieId}")
    suspend fun getMovieDetail(
        @Path("movieId") movieId: String
    ): Response<MovieDetailDto>
}