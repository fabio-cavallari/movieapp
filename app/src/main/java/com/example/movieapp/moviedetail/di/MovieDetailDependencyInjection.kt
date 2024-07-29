package com.example.movieapp.moviedetail.di

import com.example.movieapp.moviedetail.data.repository.MovieDetailRepository
import com.example.movieapp.moviedetail.data.repository.MovieDetailRepositoryImpl
import com.example.movieapp.moviedetail.presentation.viewmodel.MovieDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

object MovieDetailDependencyInjection {
    val movieDetailModules = module {
        factoryOf(::MovieDetailRepositoryImpl) { bind<MovieDetailRepository>() }
        viewModelOf(::MovieDetailViewModel)
    }
}