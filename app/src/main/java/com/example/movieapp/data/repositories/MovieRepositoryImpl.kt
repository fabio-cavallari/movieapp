package com.example.movieapp.data.repositories

import com.example.movieapp.data.Result
import com.example.movieapp.data.dto.MovieResponseDto
import com.example.movieapp.network.remoteproviders.MovieDbRemoteProvider
import com.example.movieapp.network.remoteproviders.MovieDbRemoteProviderImpl

class MovieRepositoryImpl(): MovieListRepository {
    private val remoteProvider: MovieDbRemoteProvider = MovieDbRemoteProviderImpl()
    override suspend fun getMovieList(page: Int): Result<MovieResponseDto> {
        val response = remoteProvider.getMovieList(page)
        return if (response.isSuccessful && response.body() != null) {
            Result.Success(response.body()!!)
        } else {
            Result.Error(
                message = response.message(),
                errorCode = response.code().toString()
            )
        }
    }
}