package com.example.movieapp.shared.utils

sealed class Result<out R> {
    class Success<out T>(val data: T) : Result<T>()
    open class Error(
        val message: String? = null,
        val errorCode: String? = null,
    ) : Result<Nothing>()
}