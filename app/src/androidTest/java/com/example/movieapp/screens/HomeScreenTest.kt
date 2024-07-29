package com.example.movieapp.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.movieapp.home.presentation.intent.HomeIntent
import com.example.movieapp.home.presentation.screen.HomeScreen
import com.example.movieapp.home.presentation.state.HomeScreenUiState
import com.example.movieapp.home.presentation.state.HomeState
import com.example.movieapp.shared.presentation.sampledata.movieListSample
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeScreen_showsLoading_whenLoadingState() {
        val uiState = HomeScreenUiState(movieList = movieListSample, uiState = HomeState.LOADING)
        composeTestRule.setContent {
            HomeScreen(state = uiState) {}
        }
        composeTestRule.onNodeWithTag("loadingComponentTag").assertIsDisplayed()
    }

    @Test
    fun homeScreen_showsSuccess_whenRetryClickSucceeds() {
        composeTestRule.setContent {
            var uiState by remember { mutableStateOf(HomeScreenUiState(movieList = linkedSetOf(), uiState = HomeState.ERROR)) }
            HomeScreen(state = uiState) { homeIntent ->
                when (homeIntent) {
                    HomeIntent.GetMovieList -> {
                        uiState = HomeScreenUiState(movieList = movieListSample, uiState = HomeState.SUCCESS)
                    }
                    else -> {}
                }
            }
        }
        composeTestRule.onNodeWithTag("errorComponentRetryButton").performClick()

        composeTestRule.onNodeWithTag("homeScreenSuccessColumn").assertIsDisplayed()
    }

    @Test
    fun homeScreen_showsMovieCards_whenSuccessState() {
        val uiState = HomeScreenUiState(movieList = movieListSample, uiState = HomeState.SUCCESS)
        composeTestRule.setContent {
            HomeScreen(state = uiState)
        }

        movieListSample.forEach { movie ->
            composeTestRule.onNodeWithText(movie.title).assertIsDisplayed()
        }
    }

    @Test
    fun homeScreen_showsMovieCardsAndLoading_whenPagingState() {
        val uiState = HomeScreenUiState(movieList = movieListSample, uiState = HomeState.PAGING)
        composeTestRule.setContent {
            HomeScreen(state = uiState)
        }

        movieListSample.forEach { movie ->
            composeTestRule.onNodeWithText(movie.title).assertIsDisplayed()
        }
        composeTestRule.onNodeWithTag("homeScreenPagingIndicator").assertIsDisplayed()
    }
}
