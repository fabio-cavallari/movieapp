package com.example.movieapp.moviedetail.domain

data class MovieDetail(
    val belongsToCollection: Collection? = null,
    val genres: List<Genre> = listOf(),
    val homepage: String = "",
    val id: Int = 0,
    val imdbId: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterPath: String = "",
    val productionCompanies: List<ProductionCompany> = listOf(),
    val releaseDate: String = "",
    val runtime: Int = 0,
    val tagline: String = "",
    val title: String = "",
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0
)