package com.example.movieapp.di

import com.example.movieapp.data.repositories.MovieListRepository
import com.example.movieapp.data.repositories.MovieListRepositoryImpl
import com.example.movieapp.network.clients.MovieDbClient
import com.example.movieapp.network.remoteproviders.MovieDbRemoteProvider
import com.example.movieapp.network.remoteproviders.MovieDbRemoteProviderImpl
import com.example.movieapp.viewmodels.HomeScreenViewModel
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object AppModule {
    val modules = module {
        factoryOf(::MovieDbRemoteProviderImpl) { bind<MovieDbRemoteProvider>() }
        factoryOf(::MovieListRepositoryImpl) { bind<MovieListRepository>() }
        viewModelOf(::HomeScreenViewModel)

        factory<OkHttpClient> {params ->
            val headers = params.get<Map<String, String>>()
            OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val originalRequest: Request = chain.request()
                    val builder = originalRequest.newBuilder()
                    headers.forEach { header ->
                        builder.addHeader(header.key, header.value)
                    }
                    chain.proceed(builder.build())
                }
                .build()
        }

        factory<MovieDbClient> {
            val headersParams = {
                parametersOf(mapOf("Authorization" to "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxYjE3N2UwNjM5Mzk3MTQ4Y2I2ZmNmMGI5MzAyYTY4ZCIsIm5iZiI6MTcyMTc2NzQ4Ni4wMTUxNTEsInN1YiI6IjYyNzg1YjUxYTgwMjM2MTQxNDYxMmJjNSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.iVALUHYHr3Ah-zjKzWEeDLVNFKmq_WCg446M-g_HO0w"))
            }
            Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl("https://api.themoviedb.org")
                .client(get(parameters = headersParams))
                .build()
                .create(MovieDbClient::class.java)
        }
    }
}