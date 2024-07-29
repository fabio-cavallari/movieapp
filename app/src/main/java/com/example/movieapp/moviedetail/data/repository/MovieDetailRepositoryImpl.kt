package com.example.movieapp.moviedetail.data.repository

import com.example.movieapp.network.handleApiResponse
import com.example.movieapp.shared.utils.Result
import com.example.movieapp.moviedetail.domain.MovieDetail
import com.example.movieapp.moviedetail.domain.asDomainModel
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