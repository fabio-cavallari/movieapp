package com.example.movieapp.model

data class MovieDetail(
    val belongsToCollection: Collection?,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val imdbId: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val productionCompanies: List<ProductionCompany>,
    val releaseDate: String,
    val runtime: Int?,
    val tagline: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int
)