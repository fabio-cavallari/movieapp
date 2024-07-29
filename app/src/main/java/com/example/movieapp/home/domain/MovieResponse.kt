package com.example.movieapp.home.domain

data class MovieResponse(
    val page: Int,
    val results: List<Movie>,
    val totalPages: Int,
)

