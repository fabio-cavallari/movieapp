package com.example.movieapp.data.repositories

import com.example.movieapp.data.Result
import com.example.movieapp.model.MovieDetail
import com.example.movieapp.model.asDomainModel
import com.example.movieapp.network.remoteproviders.MovieDbRemoteProvider

class MovieDetailRepositoryImpl(
    private val remoteProvider: MovieDbRemoteProvider
): MovieDetailRepository {
    override suspend fun getMovieDetail(movieId: Int): Result<MovieDetail> {
        return handleApiResponse(
            apiCall = { remoteProvider.getMovieDetail(movieId) },
            mapToDomain = { it.asDomainModel() }
        )
    }
}