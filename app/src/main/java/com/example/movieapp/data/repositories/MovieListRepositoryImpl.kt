package com.example.movieapp.data.repositories

import com.example.movieapp.data.Result
import com.example.movieapp.model.MovieResponse
import com.example.movieapp.model.asDomainModel
import com.example.movieapp.network.remoteproviders.MovieDbRemoteProvider

class MovieListRepositoryImpl(private val remoteProvider: MovieDbRemoteProvider) : MovieListRepository {

    override suspend fun getMovieList(page: Int): Result<MovieResponse> {
        return handleApiResponse(
            apiCall = { remoteProvider.getMovieList(page) },
            mapToDomain = { it.asDomainModel() }
        )
    }
}
