package com.example.movieapp

import android.app.Application
import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {

        }
    }
}