package com.example.movieapp.viewmodels

import com.example.movieapp.data.Result

sealed class UIStateClass<T> {
    open class Success<T>(val data: T) : UIStateClass<T>()
    open class Error<T>(val errorData: Result.Error? = null) : UIStateClass<T>()
    open class Loading<T> : UIStateClass<T>()
}