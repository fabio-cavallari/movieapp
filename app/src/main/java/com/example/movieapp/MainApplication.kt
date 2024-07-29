package com.example.movieapp

import android.app.Application
import com.example.movieapp.home.di.HomeDependencyInjection.homeModules
import com.example.movieapp.moviedetail.di.MovieDetailDependencyInjection.movieDetailModules
import com.example.movieapp.network.di.NetworkDependencyInjection.networkModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(listOf(homeModules, movieDetailModules, networkModules))
        }
    }
}