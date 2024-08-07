package com.example.movieapp.home.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.movieapp.R
import com.example.movieapp.home.domain.Movie
import com.example.movieapp.shared.presentation.components.LoadingComponent
import com.example.movieapp.shared.presentation.sampledata.movieSample2
import com.example.movieapp.shared.utils.formatDate

@Composable
fun MovieCard(movie: Movie, onMovieClick: (Movie) -> Unit = {}) {
    Card(
        Modifier
            .height(200.dp)
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onMovieClick(movie) },
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.inverseOnSurface),

    ) {
        Row(
            Modifier.background(color = Color.Transparent)
        ) {
            Box(
                Modifier
                    .padding(16.dp)
                    .fillMaxHeight()
                    .width(100.dp)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                SubcomposeAsyncImage(
                    model = "https://image.tmdb.org/t/p/original${movie.poster}",
                    contentDescription = "movieCardPoster",
                    contentScale = ContentScale.Fit,
                    loading = { LoadingComponent() },
                )
            }
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = movie.title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight(700),
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = formatDate(movie.date),
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_popcorn),
                        contentDescription = "movieCardPopcornIcon"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(id = R.string.movie_card_rate, movie.rate),
                        fontWeight = FontWeight(500),
                        color = MaterialTheme.colorScheme.secondary
                    )
                }

            }
        }
    }
}

@Preview
@Composable
fun MovieCardPrev() {
    MovieCard(movieSample2)
}