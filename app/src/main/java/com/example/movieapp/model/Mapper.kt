package com.example.movieapp.model

import com.example.movieapp.data.dto.CollectionDto
import com.example.movieapp.data.dto.GenreDto
import com.example.movieapp.data.dto.MovieDetailDto
import com.example.movieapp.data.dto.MovieDto
import com.example.movieapp.data.dto.MovieResponseDto
import com.example.movieapp.data.dto.ProductionCompanyDto

fun MovieDto.asDomainModel() = Movie(id, title, date, poster, rate)

fun List<MovieDto>.asDomainModel() = map { it.asDomainModel() }

fun MovieResponseDto.asDomainModel() = MovieResponse(page, results.asDomainModel(), totalPages)

fun CollectionDto.asDomainModel() = Collection(id, name, posterPath ?: "")

fun GenreDto.asDomainModel() = Genre(id, name)

fun List<GenreDto>.asDomainModel() = map { it.asDomainModel() }

fun ProductionCompanyDto.asDomainModel() = ProductionCompany(id, logoPath ?: "")

fun List<ProductionCompanyDto>.asDomainModel() = map { it.asDomainModel() }

fun MovieDetailDto.asDomainModel() = MovieDetail(
    belongsToCollection = belongsToCollection?.asDomainModel(),
    genres = genres.asDomainModel(),
    homepage = homepage ?: "",
    id = id,
    imdbId = imdbId ?: "",
    originalTitle = originalTitle,
    overview = overview ?: "",
    popularity = popularity,
    posterPath = posterPath ?: "",
    productionCompanies = productionCompanies.asDomainModel(),
    releaseDate = releaseDate ?: "",
    runtime = runtime,
    tagline = tagline ?: "",
    title = title,
    voteAverage = voteAverage,
    voteCount = voteCount
)