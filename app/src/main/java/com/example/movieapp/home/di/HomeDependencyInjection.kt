package com.example.movieapp.home.di

import com.example.movieapp.home.data.repository.MovieListRepository
import com.example.movieapp.home.data.repository.MovieListRepositoryImpl
import com.example.movieapp.home.presentation.viewmodel.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

object HomeDependencyInjection {
    val homeModules = module {
        factoryOf(::MovieListRepositoryImpl) { bind<MovieListRepository>() }
        viewModelOf(::HomeScreenViewModel)
    }
}