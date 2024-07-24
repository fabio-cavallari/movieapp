package com.example.movieapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.movieapp.R
import com.example.movieapp.model.Movie
import com.example.movieapp.model.movieSample

@Composable
fun MovieCard(movie: Movie) {
    Card {
        Row(
            Modifier
                .height(200.dp)
                .fillMaxWidth()
        ) {
            Box(
                Modifier
                    .fillMaxHeight()
                    .background(color = MaterialTheme.colorScheme.primary)
            ) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/original${movie.poster}",
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    placeholder = painterResource(id = R.drawable.p)
                )
            }

            Column(
                Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.secondary)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = movie.title)
                Text(text = movie.date)
                Text(text = movie.rate)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MovieCardPrev() {
    MovieCard(movieSample)
}