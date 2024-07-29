package com.example.movieapp.repository

import com.example.movieapp.home.data.model.MovieResponseDto
import com.example.movieapp.home.data.repository.MovieListRepositoryImpl
import com.example.movieapp.home.domain.asDomainModel
import com.example.movieapp.network.remoteproviders.MovieDbRemoteProvider
import com.example.movieapp.shared.presentation.sampledata.movieResponseDtoSample
import com.example.movieapp.shared.utils.Result
import com.example.movieapp.utils.MainCoroutineRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class MovieListRepositoryTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val remoteProvider: MovieDbRemoteProvider = mockk()
    private val repository = MovieListRepositoryImpl(remoteProvider)

    @Test
    fun `getMovieList should return Success when api call is successful`() = runTest {
        // GIVEN
        val fakeRetrofitResponse = Response.success(movieResponseDtoSample)
        coEvery { remoteProvider.getMovieList(any()) } returns Pair(fakeRetrofitResponse, null)

        // WHEN
        val result = repository.getMovieList(1)

        // THEN
        val expectedResult = Result.Success(movieResponseDtoSample.asDomainModel())
        assertEquals(expectedResult::class.java, result.javaClass)
        assertEquals(
            expectedResult.data.results.first().title,
            movieResponseDtoSample.results.first().title
        )
    }

    @Test
    fun `getMovieList should return Error when api response is unsuccessful`() = runTest {
        // GIVEN
        val retrofitResponse = Response.error<MovieResponseDto>(500, mockk(relaxed = true))
        coEvery { remoteProvider.getMovieList(any()) } returns Pair(retrofitResponse, null)

        // WHEN
        val result = repository.getMovieList(1)

        // THEN
        assertEquals(Result.Error::class.java, result.javaClass)
        assertEquals((result as Result.Error).errorCode, retrofitResponse.code().toString())
    }
}