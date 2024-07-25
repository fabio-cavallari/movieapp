package com.example.movieapp.di

import com.example.movieapp.data.repositories.MovieListRepository
import com.example.movieapp.data.repositories.MovieRepositoryImpl
import com.example.movieapp.network.remoteproviders.MovieDbRemoteProvider
import com.example.movieapp.network.remoteproviders.MovieDbRemoteProviderImpl
import com.example.movieapp.viewmodels.MovieListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

object AppModule {
    val modules = module {
        factoryOf(::MovieDbRemoteProviderImpl) { bind<MovieDbRemoteProvider>() }
        factoryOf(::MovieRepositoryImpl) { bind<MovieListRepository>() }
        viewModelOf(::MovieListViewModel)
    }
}