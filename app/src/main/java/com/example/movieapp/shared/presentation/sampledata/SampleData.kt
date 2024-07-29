package com.example.movieapp.shared.presentation.sampledata

import com.example.movieapp.home.data.model.MovieDto
import com.example.movieapp.home.data.model.MovieResponseDto
import com.example.movieapp.home.domain.Movie
import com.example.movieapp.moviedetail.domain.Collection
import com.example.movieapp.moviedetail.domain.Genre
import com.example.movieapp.moviedetail.domain.MovieDetail
import com.example.movieapp.moviedetail.domain.ProductionCompany

val movieSample = Movie(
    id = 1,
    title = "Inside Out 2",
    date = "2024-06-11",
    rate = 7.645,
    poster = "/vpnVM9B6NMmQpWeZvzLvDESb2QY.jpg"
)

val movieSample2 = Movie(
    id = 2,
    title = "Furiosa: A Mad Max Saga",
    date = "2024-06-11",
    rate = 7.645,
    poster = "/iADOJ8Zymht2JPMoy3R7xceZprc.jpg"
)

val movieSample3 = Movie(
    id = 3,
    title = "Kingdom of the Planet of the Apes",
    date = "2024-06-11",
    rate = 7.645,
    poster = "/gKkl37BQuKTanygYQG1pyYgLVgf.jpg"
)

val movieListSample = linkedSetOf(movieSample, movieSample2, movieSample3, movieSample, movieSample2, movieSample3)

val movieDtoSample = MovieDto(
    id = 1,
    title = "title",
    date = "date",
    rate = 1.0,
    poster = "poster"
)

val movieResponseDtoSample = MovieResponseDto(
    page = 1,
    results = listOf(movieDtoSample),
    totalPages = 10
)

val movieDetailSample = MovieDetail(
    belongsToCollection = Collection(
        id = 1022790,
        name = "Inside Out Collection",
        posterPath = "/Apr19lGxP7gm6y2HQX0kqOXTtqC.jpg",
    ),
    genres = listOf(
        Genre(
            id = 16,
            name = "Animation"
        ), Genre(
            id = 10751,
            name = "Family"
        ), Genre(
            id = 12,
            name = "Adventure"
        )
    ),
    homepage = "https://movies.disney.com/inside-out-2",
    id = 1022789,
    imdbId = "tt22022452",
    overview = "Teenager Riley's mind headquarters is undergoing a sudden demolition to make room for something entirely unexpected: new Emotions! Joy, Sadness, Anger, Fear and Disgust, who’ve long been running a successful operation by all accounts, aren’t sure how to feel when Anxiety shows up. And it looks like she’s not alone.",
    popularity = 4659.516,
    posterPath = "/vpnVM9B6NMmQpWeZvzLvDESb2QY.jpg",
    productionCompanies = listOf(
        ProductionCompany(
            id = 2,
            logoPath = "/wdrCwmRnLFJhEoH8GSfymY85KHT.png",
        ), ProductionCompany(
            id = 3,
            logoPath = "/1TjvGVDMYsj6JBxOAkUHpPEwLf7.png",
        )
    ),
    releaseDate = "2024-06-11",
    runtime = 97,
    tagline = "Make room for new emotions.",
    title = "Inside Out 2",
    voteAverage = 7.63,
    voteCount = 2118
)
