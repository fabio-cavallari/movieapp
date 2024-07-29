package com.example.movieapp.moviedetail.domain

import com.example.movieapp.moviedetail.data.model.CollectionDto
import com.example.movieapp.moviedetail.data.model.GenreDto
import com.example.movieapp.moviedetail.data.model.MovieDetailDto
import com.example.movieapp.home.data.model.MovieDto
import com.example.movieapp.home.data.model.MovieResponseDto
import com.example.movieapp.moviedetail.data.model.ProductionCompanyDto
import com.example.movieapp.home.domain.Movie
import com.example.movieapp.home.domain.MovieResponse

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
    runtime = runtime ?: 0,
    tagline = tagline ?: "",
    title = title,
    voteAverage = voteAverage,
    voteCount = voteCount
)