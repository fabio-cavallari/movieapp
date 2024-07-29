package com.example.movieapp.network

import com.example.movieapp.shared.utils.Result
import retrofit2.Response

suspend fun <T, R> handleApiResponse(
    apiCall: suspend () -> Pair<Response<T>?, Throwable?>,
    mapToDomain: (T) -> R
): Result<R> {
    val response = apiCall()
    val throwable = response.second
    if (throwable != null) {
        return Result.Error(throwable.message ?: "Unknown Error")
    }

    val retrofitResponse = response.first
    return if (retrofitResponse != null && retrofitResponse.isSuccessful && retrofitResponse.body() != null) {
        val domainModel = mapToDomain(retrofitResponse.body()!!)
        Result.Success(domainModel)
    } else {
        Result.Error(
            message = retrofitResponse?.message() ?: "Unknown Error",
            errorCode = retrofitResponse?.code()?.toString()
        )
    }
}