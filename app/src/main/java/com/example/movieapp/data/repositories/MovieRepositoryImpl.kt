package com.example.movieapp.data.repositories

import com.example.movieapp.data.Result
import com.example.movieapp.model.MovieResponse
import com.example.movieapp.model.asDomainModel
import com.example.movieapp.network.remoteproviders.MovieDbRemoteProvider

class MovieRepositoryImpl(private val remoteProvider: MovieDbRemoteProvider) : MovieListRepository {

    override suspend fun getMovieList(page: Int): Result<MovieResponse> {
        val response = remoteProvider.getMovieList(page)
        if (response.second != null) {
            return Result.Error(response.second!!.message, null)
        }
        if (response.first != null) {
            val retrofitResponse = response.first!!
            return if (retrofitResponse.isSuccessful && retrofitResponse.body() != null) {
                val movieResponse = retrofitResponse.body()!!.asDomainModel()
                Result.Success(movieResponse)
            } else {
                Result.Error(
                    message = retrofitResponse.message(),
                    errorCode = retrofitResponse.code().toString()
                )
            }
        }
        return Result.Error()
    }
}