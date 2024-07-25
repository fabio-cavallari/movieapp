package com.example.movieapp.network.remoteproviders

import com.example.movieapp.data.dto.MovieResponseDto
import com.example.movieapp.network.clients.MovieDbClient
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class MovieDbRemoteProviderImpl: MovieDbRemoteProvider {

    private val baseClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val originalRequest: Request = chain.request()
            val requestWithHeaders = originalRequest.newBuilder()
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxYjE3N2UwNjM5Mzk3MTQ4Y2I2ZmNmMGI5MzAyYTY4ZCIsIm5iZiI6MTcyMTc2NzQ4Ni4wMTUxNTEsInN1YiI6IjYyNzg1YjUxYTgwMjM2MTQxNDYxMmJjNSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.iVALUHYHr3Ah-zjKzWEeDLVNFKmq_WCg446M-g_HO0w")
                .build()

            chain.proceed(requestWithHeaders)
        }
        .build()

    private val retrofit = Retrofit
        .Builder()
        .addConverterFactory(JacksonConverterFactory.create())
        .baseUrl("https://api.themoviedb.org")
        .client(baseClient)
        .build()

    private val client = retrofit.create(MovieDbClient::class.java)

    override suspend fun getMovieList(page: Int): Pair<Response<MovieResponseDto>?, Exception?> {
        return try {
            Pair(client.getMovieList(page), null)
        } catch (e: Exception) {
            Pair(null, e)
        }
    }
}