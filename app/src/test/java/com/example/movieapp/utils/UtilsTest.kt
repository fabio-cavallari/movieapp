package com.example.movieapp.utils

import com.example.movieapp.home.data.model.MovieResponseDto
import com.example.movieapp.home.domain.asDomainModel
import com.example.movieapp.network.handleApiResponse
import com.example.movieapp.shared.presentation.sampledata.movieResponseDtoSample
import com.example.movieapp.shared.utils.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class UtilsTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Test
    fun `handleApiResponse should return Success with mapped domain model when api call is retrofit success`() =
        runTest {
            // GIVEN
            val fakeRetrofitResponse = Response.success<MovieResponseDto>(movieResponseDtoSample)
            val apiCallMock: suspend () -> Pair<Response<MovieResponseDto>?, Throwable?> = mockk()
            coEvery { apiCallMock() } returns Pair(fakeRetrofitResponse, null)

            // WHEN
            val result = handleApiResponse(apiCallMock) { it.asDomainModel() }

            // THEN
            assertEquals(Result.Success::class.java, result.javaClass)
            assertEquals(
                movieResponseDtoSample.results.first().title,
                (result as Result.Success).data.results.first().title
            )
        }

    @Test
    fun `handleApiResponse should return Error with error code when api call is retrofit error`() =
        runTest {
            // GIVEN
            val errorCode = 500
            val responseBodyMock: ResponseBody = mockk(relaxed = true)
            val fakeRetrofitResponse = Response.error<MovieResponseDto>(errorCode, responseBodyMock)
            val apiCallMock: suspend () -> Pair<Response<MovieResponseDto>?, Throwable?> = mockk()
            coEvery { apiCallMock() } returns Pair(fakeRetrofitResponse, null)

            // WHEN
            val result = handleApiResponse(apiCallMock, MovieResponseDto::asDomainModel)

            // THEN
            assertEquals(Result.Error::class.java, result.javaClass)
            assertEquals(errorCode.toString(), (result as Result.Error).errorCode)
        }

    @Test
    fun `handleApiResponse should return Error with error message when api call fails`() =
        runTest {
            // GIVEN
            val errorMessage = "Unexpected Error"
            val fakeThrowable = Throwable(message = errorMessage)
            val apiCallMock: suspend () -> Pair<Response<MovieResponseDto>?, Throwable?> = mockk()
            coEvery { apiCallMock() } returns Pair(null, fakeThrowable)

            // WHEN
            val result = handleApiResponse(apiCallMock, MovieResponseDto::asDomainModel)

            // THEN
            assertEquals(Result.Error::class.java, result.javaClass)
            assertEquals(errorMessage, (result as Result.Error).message)
        }
}