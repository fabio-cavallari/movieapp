package com.example.movieapp.moviedetail.di

import com.example.movieapp.BuildConfig
import com.example.movieapp.moviedetail.data.repository.MovieDetailRepository
import com.example.movieapp.moviedetail.data.repository.MovieDetailRepositoryImpl
import com.example.movieapp.home.data.repository.MovieListRepository
import com.example.movieapp.home.data.repository.MovieListRepositoryImpl
import com.example.movieapp.network.clients.MovieDbClient
import com.example.movieapp.network.remoteproviders.MovieDbRemoteProvider
import com.example.movieapp.network.remoteproviders.MovieDbRemoteProviderImpl
import com.example.movieapp.home.presentation.viewmodel.HomeScreenViewModel
import com.example.movieapp.moviedetail.presentation.viewmodel.MovieDetailViewModel
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object MovieDetailDependencyInjection {
    val movieDetailModules = module {
        factoryOf(::MovieListRepositoryImpl) { bind<MovieListRepository>() }
        viewModelOf(::HomeScreenViewModel)
    }
}