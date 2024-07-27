package com.example.movieapp.data.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class MovieDetailDto(
    @JsonProperty("belongs_to_collection") val belongsToCollection: CollectionDto?,
    val genres: List<GenreDto>,
    val homepage: String?,
    val id: Int,
    @JsonProperty("imdb_id") val imdbId: String?,
    @JsonProperty("original_title") val originalTitle: String,
    val overview: String?,
    val popularity: Double,
    @JsonProperty("poster_path") val posterPath: String?,
    @JsonProperty("production_companies") val productionCompanies: List<ProductionCompanyDto>,
    @JsonProperty("release_date") val releaseDate: String?,
    val runtime: Int?,
    val tagline: String?,
    val title: String,
    @JsonProperty("vote_average") val voteAverage: Double,
    @JsonProperty("vote_count") val voteCount: Int
)