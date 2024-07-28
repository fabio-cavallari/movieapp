package com.example.movieapp.model

import com.example.movieapp.data.dto.CollectionDto
import com.example.movieapp.data.dto.GenreDto
import com.example.movieapp.data.dto.MovieDetailDto
import com.example.movieapp.data.dto.MovieDto
import com.example.movieapp.data.dto.MovieResponseDto
import com.example.movieapp.data.dto.ProductionCompanyDto

fun MovieDto.asDomainModel() = Movie(id, title, date, poster, rate)

fun MovieResponseDto.asDomainModel() = MovieResponse(
    page,
    results.map { it.asDomainModel() },
    totalPages
)

fun CollectionDto.asDomainModel() = Collection(id, name, posterPath ?: "")

fun GenreDto.asDomainModel() = Genre(id, name)

fun ProductionCompanyDto.asDomainModel() = ProductionCompany(id, logoPath ?: "")

fun MovieDetailDto.asDomainModel() = MovieDetail(
    belongsToCollection = belongsToCollection?.asDomainModel(),
    genres = genres.map { it.asDomainModel() },
    homepage = homepage ?: "",
    id = id,
    imdbId = imdbId ?: "",
    originalTitle = originalTitle,
    overview = overview ?: "",
    popularity = popularity,
    posterPath = posterPath ?: "",
    productionCompanies = productionCompanies.map { it.asDomainModel() },
    releaseDate = releaseDate ?: "",
    runtime = runtime,
    tagline = tagline ?: "",
    title = title,
    voteAverage = voteAverage,
    voteCount = voteCount
)