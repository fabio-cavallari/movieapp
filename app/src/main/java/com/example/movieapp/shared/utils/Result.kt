package com.example.movieapp.shared.utils

sealed class Result<out R> {
    class Success<out T>(val data: T) : Result<T>()
    open class Error(
        val message: String? = null,
        val errorCode: String? = null,
    ) : Result<Nothing>()
}

inline fun <T> Result<T>.onSuccess(action: (T) -> Unit): Result<T> {
    if (this is Result.Success) action(data)
    return this
}

inline fun <T> Result<T>.onError(action: (Result.Error) -> Unit): Result<T> {
    if (this is Result.Error) action(this)
    return this
}