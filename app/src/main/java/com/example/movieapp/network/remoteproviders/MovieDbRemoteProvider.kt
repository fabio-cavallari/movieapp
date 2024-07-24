package com.example.movieapp.network.remoteproviders

import com.example.movieapp.data.dto.MovieResponseDto
import retrofit2.Response

interface MovieDbRemoteProvider {
    suspend fun getMovieList(page: Int): Response<MovieResponseDto>
}