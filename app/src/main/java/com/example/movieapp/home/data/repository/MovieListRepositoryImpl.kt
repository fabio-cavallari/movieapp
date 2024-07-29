package com.example.movieapp.home.data.repository

import com.example.movieapp.network.handleApiResponse
import com.example.movieapp.shared.utils.Result
import com.example.movieapp.home.domain.MovieResponse
import com.example.movieapp.moviedetail.domain.asDomainModel
import com.example.movieapp.network.remoteproviders.MovieDbRemoteProvider

class MovieListRepositoryImpl(private val remoteProvider: MovieDbRemoteProvider) :
    MovieListRepository {

    override suspend fun getMovieList(page: Int): Result<MovieResponse> {
        return handleApiResponse(
            apiCall = { remoteProvider.getMovieList(page) },
            mapToDomain = { it.asDomainModel() }
        )
    }
}
