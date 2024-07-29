package com.example.movieapp.screens

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.movieapp.R
import com.example.movieapp.components.ErrorComponent
import com.example.movieapp.components.LoadingComponent
import com.example.movieapp.intents.MovieDetailIntent
import com.example.movieapp.model.MovieDetail
import com.example.movieapp.model.movieDetailSample
import com.example.movieapp.states.MovieDetailUiState
import com.example.movieapp.states.MovieState
import com.example.movieapp.utils.formatDate
import com.example.movieapp.viewmodels.MovieDetailViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MovieDetailScreen(
    navController: NavHostController,
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: MovieDetailViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    MovieDetailScreen(state, animatedVisibilityScope) { movieDetailIntent ->
        when (movieDetailIntent) {
            is MovieDetailIntent.GoBack -> navController.popBackStack()
            else -> viewModel.onIntent(movieDetailIntent)
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MovieDetailScreen(
    state: MovieDetailUiState,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onIntent: (MovieDetailIntent) -> Unit = {}
) {
    when (state.uiState) {
        MovieState.LOADING -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)

            ) {
                AsyncImage(
                    modifier = Modifier
                        .sharedElement(
                            state = rememberSharedContentState(key = state.movieDetail.posterPath),
                            animatedVisibilityScope = animatedVisibilityScope
                        ),
                    model = "https://image.tmdb.org/t/p/original${state.movieDetail.posterPath}",
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                )
                Text(
                    modifier = Modifier
                        .sharedElement(
                            state = rememberSharedContentState(key = state.movieDetail.title),
                            animatedVisibilityScope = animatedVisibilityScope
                        ),
                    text = state.movieDetail.title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight(700),
                    color = MaterialTheme.colorScheme.primary
                )
                LoadingComponent()
            }
        }

        MovieState.ERROR -> ErrorComponent(
            errorMessage = stringResource(id = R.string.generic_error_message),
            buttonText = stringResource(
                id = R.string.generic_error_button_text
            ),
            onButtonClick = { onIntent(MovieDetailIntent.GetMovieDetail) }
        )

        MovieState.SUCCESS -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)

            ) {
                AsyncImage(
                    modifier = Modifier
                        .sharedElement(
                            state = rememberSharedContentState(key = state.movieDetail.posterPath),
                            animatedVisibilityScope = animatedVisibilityScope
                        ),
                    model = "https://image.tmdb.org/t/p/original${state.movieDetail.posterPath}",
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                )
                Text(
                    modifier = Modifier
                        .sharedElement(
                            state = rememberSharedContentState(key = state.movieDetail.title),
                            animatedVisibilityScope = animatedVisibilityScope
                        ),
                    text = state.movieDetail.title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight(700),
                    color = MaterialTheme.colorScheme.primary
                )
                Text(text = state.movieDetail.tagline, color = MaterialTheme.colorScheme.secondary)
                Text(
                    text = formatDate(state.movieDetail.releaseDate),
                    color = MaterialTheme.colorScheme.secondary
                )
                if (state.movieDetail.genres.isNotEmpty()) {
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(state.movieDetail.genres) { genre ->
                            Box(
                                modifier = Modifier
                                    .background(
                                        color = MaterialTheme.colorScheme.secondaryContainer,
                                        shape = RoundedCornerShape(100.dp)
                                    )
                                    .padding(horizontal = 16.dp)
                            ) {
                                Text(
                                    text = genre.name,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer
                                )
                            }
                        }
                    }
                }
                Text(text = state.movieDetail.overview, color = MaterialTheme.colorScheme.secondary)
                Text(
                    text = stringResource(
                        id = R.string.product_detail_duration,
                        state.movieDetail.runtime
                    ),
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = stringResource(
                        id = R.string.product_detail_vote_average,
                        state.movieDetail.voteAverage
                    ),
                    color = MaterialTheme.colorScheme.secondary
                )
                if (state.movieDetail.productionCompanies.isNotEmpty()) {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        items(state.movieDetail.productionCompanies) { productionCompany ->
                            Box(
                                Modifier
                                    .width(100.dp)
                                    .height(100.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                AsyncImage(
                                    model = "https://image.tmdb.org/t/p/original${productionCompany.logoPath}",
                                    contentDescription = null,
                                    contentScale = ContentScale.Fit,
                                )
                            }
                        }
                    }
                }

            }
        }
    }
}

//@Preview(showSystemUi = true)
//@Composable
//private fun MovieDetailScreenSuccessPreview() {
//    MovieDetailScreen(state = MovieDetailUiState(MovieState.SUCCESS, movieDetailSample))
//}