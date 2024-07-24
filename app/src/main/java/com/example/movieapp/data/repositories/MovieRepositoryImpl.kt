package com.example.movieapp.data.repositories

import com.example.movieapp.data.Result
import com.example.movieapp.model.MovieResponse
import com.example.movieapp.model.asDomainModel
import com.example.movieapp.network.remoteproviders.MovieDbRemoteProvider
import com.example.movieapp.network.remoteproviders.MovieDbRemoteProviderImpl

class MovieRepositoryImpl(): MovieListRepository {
    private val remoteProvider: MovieDbRemoteProvider = MovieDbRemoteProviderImpl()
    override suspend fun getMovieList(page: Int): Result<MovieResponse> {
        val response = remoteProvider.getMovieList(page)
        return if (response.isSuccessful && response.body() != null) {
            val movieResponse = response.body()!!.asDomainModel()
            Result.Success(movieResponse)
        } else {
            Result.Error(
                message = response.message(),
                errorCode = response.code().toString()
            )
        }
    }
}