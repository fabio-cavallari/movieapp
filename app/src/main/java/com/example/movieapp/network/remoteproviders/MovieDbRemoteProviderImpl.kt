package com.example.movieapp.network.remoteproviders

import com.example.movieapp.data.dto.MovieDetailDto
import com.example.movieapp.data.dto.MovieResponseDto
import com.example.movieapp.network.clients.MovieDbClient
import retrofit2.Response

class MovieDbRemoteProviderImpl(private val client: MovieDbClient): MovieDbRemoteProvider {
    override suspend fun getMovieList(page: Int): Pair<Response<MovieResponseDto>?, Exception?> {
        return try {
            Pair(client.getMovieList(page), null)
        } catch (e: Exception) {
            Pair(null, e)
        }
    }

    override suspend fun getMovieDetail(movieId: String): Pair<Response<MovieDetailDto>?, Exception?> {
        return try {
            Pair(client.getMovieDetail(movieId), null)
        } catch (e: Exception) {
            Pair(null, e)
        }
    }
}