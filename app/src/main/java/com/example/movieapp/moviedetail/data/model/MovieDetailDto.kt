package com.example.movieapp.moviedetail.data.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class MovieDetailDto(
    @JsonProperty("id") val id: Int,
    @JsonProperty("belongs_to_collection") val belongsToCollection: CollectionDto?,
    @JsonProperty("genres") val genres: List<GenreDto>,
    @JsonProperty("homepage") val homepage: String?,
    @JsonProperty("imdb_id") val imdbId: String?,
    @JsonProperty("original_title") val originalTitle: String,
    @JsonProperty("overview") val overview: String?,
    @JsonProperty("popularity") val popularity: Double,
    @JsonProperty("poster_path") val posterPath: String?,
    @JsonProperty("production_companies") val productionCompanies: List<ProductionCompanyDto>,
    @JsonProperty("release_date") val releaseDate: String?,
    @JsonProperty("runtime") val runtime: Int?,
    @JsonProperty("tagline") val tagline: String?,
    @JsonProperty("title") val title: String,
    @JsonProperty("vote_average") val voteAverage: Double,
    @JsonProperty("vote_count") val voteCount: Int
)