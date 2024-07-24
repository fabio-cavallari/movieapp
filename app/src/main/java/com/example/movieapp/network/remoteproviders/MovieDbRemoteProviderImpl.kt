package com.example.movieapp.network.remoteproviders

import com.example.movieapp.data.dto.MovieResponseDto
import com.example.movieapp.network.clients.MovieDbClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class MovieDbRemoteProviderImpl(): MovieDbRemoteProvider {

    private val retrofit = Retrofit
        .Builder()
        .addConverterFactory(JacksonConverterFactory.create())
        .baseUrl("https://api.themoviedb.org/3/")
        .build()

    private val client = retrofit.create(MovieDbClient::class.java)

    override suspend fun getMovieList(page: Int): Response<MovieResponseDto> {
        return client.getMovieList(page)
    }
}