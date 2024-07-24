package com.example.movieapp.network.clients

import com.example.movieapp.data.dto.MovieResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDbClient {
    @GET("/discover/movie")
    suspend fun getMovieList(
        @Query("page") page: Int
    ): Response<MovieResponseDto>
}