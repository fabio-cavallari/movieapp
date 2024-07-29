package com.example.movieapp.remoteprovider

import com.example.movieapp.home.data.model.MovieResponseDto
import com.example.movieapp.moviedetail.data.model.MovieDetailDto
import com.example.movieapp.network.clients.MovieDbClient
import com.example.movieapp.network.remoteproviders.MovieDbRemoteProviderImpl
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
class MovieDbRemoteProviderTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val movieId = 1
    private val page = 1
    private val mockClient = mockk<MovieDbClient>()
    private val remoteProvider = MovieDbRemoteProviderImpl(mockClient)

    @Test
    fun `getMovieList should return movie response and no exception when client call is successful`() = runTest {
        // GIVEN
        val fakeRetrofitResponse: Response<MovieResponseDto> = mockk()
        coEvery { mockClient.getMovieList(any()) } returns fakeRetrofitResponse

        // WHEN
        val (response, exception) = remoteProvider.getMovieList(page)

        // THEN
        assertEquals(fakeRetrofitResponse, response)
        assertEquals(null, exception)
    }
    @Test
    fun `getMovieList should return exception and no movie response when client call fails`() = runTest {
        // GIVEN
        val mockException = RuntimeException("Unexpected error")
        coEvery { mockClient.getMovieList(any()) } throws mockException

        // WHEN
        val (response, exception) = remoteProvider.getMovieList(page)

        // THEN
        assertEquals(null, response)
        assertEquals(mockException, exception)
    }

    @Test
    fun `getMovieDetail should return movie detail and no exception when client call is successful`() = runTest {
        // GIVEN
        val fakeRetrofitResponse: Response<MovieDetailDto> = mockk()
        coEvery { mockClient.getMovieDetail(movieId) } returns fakeRetrofitResponse

        // WHEN
        val (response, exception) = remoteProvider.getMovieDetail(movieId)

        // THEN
        assertEquals(fakeRetrofitResponse, response)
        assertEquals(null, exception)
    }

    @Test
    fun `getMovieDetail should return exception and no movie detail when client call fails`() = runTest {
        // GIVEN
        val mockException = RuntimeException("Unexpected error")
        coEvery { mockClient.getMovieDetail(movieId) } throws  mockException

        // WHEN
        val (response, exception) = remoteProvider.getMovieDetail(movieId)

        // THEN
        assertEquals(null, response)
        assertEquals(mockException, exception)
    }
}