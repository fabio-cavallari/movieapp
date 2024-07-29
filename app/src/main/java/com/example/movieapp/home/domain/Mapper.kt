package com.example.movieapp.home.domain

import com.example.movieapp.home.data.model.MovieDto
import com.example.movieapp.home.data.model.MovieResponseDto

fun MovieDto.asDomainModel() = Movie(id, title, date, poster, rate)

fun MovieResponseDto.asDomainModel() = MovieResponse(
    page,
    results.map { it.asDomainModel() },
    totalPages
)