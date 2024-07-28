package com.example.movieapp.di

import android.content.res.TypedArray
import com.example.movieapp.BuildConfig
import com.example.movieapp.data.repositories.MovieDetailRepository
import com.example.movieapp.data.repositories.MovieDetailRepositoryImpl
import com.example.movieapp.data.repositories.MovieListRepository
import com.example.movieapp.data.repositories.MovieListRepositoryImpl
import com.example.movieapp.network.clients.MovieDbClient
import com.example.movieapp.network.remoteproviders.MovieDbRemoteProvider
import com.example.movieapp.network.remoteproviders.MovieDbRemoteProviderImpl
import com.example.movieapp.viewmodels.HomeScreenViewModel
import com.example.movieapp.viewmodels.MovieDetailViewModel
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object KoinModules {
    private val appModules = module {
        factoryOf(::MovieDbRemoteProviderImpl) { bind<MovieDbRemoteProvider>() }
        //HomeScreen
        factoryOf(::MovieListRepositoryImpl) { bind<MovieListRepository>() }
        viewModelOf(::HomeScreenViewModel)
        //MovieDetailScreen
        factoryOf(::MovieDetailRepositoryImpl) { bind<MovieDetailRepository>() }
        viewModelOf(::MovieDetailViewModel)
    }

    private val networkModules = module {
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
                parametersOf(mapOf("Authorization" to BuildConfig.API_KEY))
            }
            Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .client(get(parameters = headersParams))
                .build()
                .create(MovieDbClient::class.java)
        }
    }

    val modules = listOf(appModules, networkModules).toTypedArray()
}