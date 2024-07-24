package com.example.movieapp.model

import com.example.movieapp.data.dto.MovieDto

data class Movie(
    val title: String,
    val date: String,
    val poster: String,
    val rate: String
)

fun MovieDto.asDomainModel() = Movie(title, date, poster, rate)

fun List<MovieDto>.asDomainModel() = map { it.asDomainModel() }

val movieSample = Movie(
    title = "Inside Out 2",
    date = "2024-06-11",
    rate = "7.645",
    poster = "/vpnVM9B6NMmQpWeZvzLvDESb2QY.jpg"
)

val movieSample2 = Movie(
    title = "Furiosa: A Mad Max Saga",
    date = "2024-06-11",
    rate = "7.645",
    poster = "/iADOJ8Zymht2JPMoy3R7xceZprc.jpg"
)

val movieSample3 = Movie(
    title = "Kingdom of the Planet of the Apes",
    date = "2024-06-11",
    rate = "7.645",
    poster = "/gKkl37BQuKTanygYQG1pyYgLVgf.jpg"
)

val movieListSample = listOf(movieSample, movieSample2, movieSample3, movieSample, movieSample2, movieSample3)