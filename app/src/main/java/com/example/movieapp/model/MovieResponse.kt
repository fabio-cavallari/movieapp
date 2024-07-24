package com.example.movieapp.model

import com.example.movieapp.data.dto.MovieResponseDto

data class MovieResponse(
    val page: Int,
    val results: List<Movie>,
    val totalPages: Int,
)

fun MovieResponseDto.asDomainModel() = MovieResponse(page, results.asDomainModel(), totalPages)

