package com.example.movieapp.moviedetail.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.example.movieapp.R
import com.example.movieapp.moviedetail.domain.movieDetailSample
import com.example.movieapp.moviedetail.presentation.intent.MovieDetailIntent
import com.example.movieapp.moviedetail.presentation.state.MovieDetailUiState
import com.example.movieapp.moviedetail.presentation.state.MovieState
import com.example.movieapp.moviedetail.presentation.viewmodel.MovieDetailViewModel
import com.example.movieapp.shared.presentation.components.ErrorComponent
import com.example.movieapp.shared.presentation.components.LoadingComponent
import com.example.movieapp.shared.utils.formatDate
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieDetailScreen(
    navController: NavHostController,
    viewModel: MovieDetailViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    MovieDetailScreen(state) { movieDetailIntent ->
        when (movieDetailIntent) {
            is MovieDetailIntent.GoBack -> navController.popBackStack()
            else -> viewModel.onIntent(movieDetailIntent)
        }
    }
}

@Composable
fun MovieDetailScreen(
    state: MovieDetailUiState,
    onIntent: (MovieDetailIntent) -> Unit = {}
) {
    when (state.uiState) {
        MovieState.LOADING -> LoadingComponent()
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
                Box(
                    Modifier
                        .height(400.dp)
                        .clip(RoundedCornerShape(16.dp))
                ) {
                    SubcomposeAsyncImage(
                        model = "https://image.tmdb.org/t/p/original${state.movieDetail.posterPath}",
                        contentDescription = "movieDetailPoster",
                        loading = {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(250.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(24.dp),
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        },
                        contentScale = ContentScale.Fit,
                    )
                }
                Text(
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
                                    contentDescription = "movieDetailProductionCompanyIcon",
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

@Preview(showSystemUi = true)
@Composable
private fun MovieDetailScreenSuccessPreview() {
    MovieDetailScreen(state = MovieDetailUiState(MovieState.SUCCESS, movieDetailSample))
}