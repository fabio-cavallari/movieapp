package com.example.movieapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.movieapp.home.data.repository.MovieListRepository
import com.example.movieapp.home.domain.MovieResponse
import com.example.movieapp.home.presentation.state.HomeState
import com.example.movieapp.home.presentation.viewmodel.HomeScreenViewModel
import com.example.movieapp.shared.presentation.sampledata.movieListSample
import com.example.movieapp.shared.utils.Result
import com.example.movieapp.utils.MainCoroutineRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeScreenViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val movieListRepositoryMock = mockk<MovieListRepository>()
    private lateinit var homeScreenViewModel: HomeScreenViewModel

    @Before
    fun setup() {
        homeScreenViewModel = HomeScreenViewModel(movieListRepositoryMock)
    }

    @Test
    fun `getMovieList should update state from LOADING to PAGING on first request and response total higher than page`() =
        runTest {
            // GIVEN
            val movieResponse = MovieResponse(
                page = 1,
                results = movieListSample.toList(),
                totalPages = 10
            )
            val fakeResult: Result<MovieResponse> = Result.Success(movieResponse)
            coEvery { movieListRepositoryMock.getMovieList(any()) } returns fakeResult
            homeScreenViewModel.resetStateForTest()

            assertEquals(HomeState.LOADING, homeScreenViewModel.state.value.uiState)
            advanceUntilIdle()

            // WHEN
            homeScreenViewModel.getMovieList()

            // THEN
            assertEquals(movieResponse.results, homeScreenViewModel.state.value.movieList.toList())
        }

    @Test
    fun `getMovieList should update state from LOADING to SUCCESS on first request and response total higher than page`() =
        runTest {
            // GIVEN
            val movieResponse = MovieResponse(
                page = 1,
                results = movieListSample.toList(),
                totalPages = 1
            )
            val fakeResult: Result<MovieResponse> = Result.Success(movieResponse)
            coEvery { movieListRepositoryMock.getMovieList(any()) } returns fakeResult
            homeScreenViewModel.resetStateForTest()

            assertEquals(HomeState.LOADING, homeScreenViewModel.state.value.uiState)

            // WHEN
            homeScreenViewModel.getMovieList()
            advanceUntilIdle()

            // THEN
            assertEquals(HomeState.SUCCESS, homeScreenViewModel.state.value.uiState)
            assertEquals(movieResponse.results, homeScreenViewModel.state.value.movieList.toList())
        }

    @Test
    fun `getMovieList should update state to ERROR after request fails`() = runTest {
        // GIVEN
        val fakeResult: Result<MovieResponse> = Result.Error()
        coEvery { movieListRepositoryMock.getMovieList(any()) } returns fakeResult
        homeScreenViewModel.resetStateForTest()

        // WHEN
        homeScreenViewModel.getMovieList()
        advanceUntilIdle()

        // THEN
        assertEquals(HomeState.ERROR, homeScreenViewModel.state.value.uiState)
    }

    @Test
    fun `getMovieList should update state to PAGING_ERROR after request fails`() = runTest {
        // GIVEN
        val fakeResult: Result<MovieResponse> = Result.Error()
        coEvery { movieListRepositoryMock.getMovieList(any()) } returns fakeResult
        homeScreenViewModel.resetStateForTest(page = 2)

        // WHEN
        homeScreenViewModel.getMovieList()
        advanceUntilIdle()

        // THEN
        assertEquals(HomeState.PAGING_ERROR, homeScreenViewModel.state.value.uiState)
    }

}