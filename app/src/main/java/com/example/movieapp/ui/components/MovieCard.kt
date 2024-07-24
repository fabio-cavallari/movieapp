package com.example.movieapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.movieapp.R
import com.example.movieapp.model.Movie
import com.example.movieapp.model.movieSample2

@Composable
fun MovieCard(movie: Movie) {
    Card(Modifier.background(color = MaterialTheme.colorScheme.primaryContainer)) {
        Row(
            Modifier
                .height(200.dp)
                .fillMaxWidth()
        ) {
            Box(
                Modifier
                    .padding(16.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(16.dp))
                    .background(color = MaterialTheme.colorScheme.primary)
            ) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/original${movie.poster}",
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    placeholder = painterResource(id = R.drawable.placeholder)
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
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = movie.date,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.popcorn_svgrepo_com),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = movie.rate,
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