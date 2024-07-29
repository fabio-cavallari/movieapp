package com.example.movieapp.home.data.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class MovieDto(
    @JsonProperty("id") val id: Int,
    @JsonProperty("title") val title: String,
    @JsonProperty("poster_path") val poster: String,
    @JsonProperty("release_date") val date: String,
    @JsonProperty("vote_average") val rate: Double,
)
