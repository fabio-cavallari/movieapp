package com.example.movieapp

import android.app.Application
import com.example.movieapp.di.KoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(*KoinModules.modules)
        }
    }
}