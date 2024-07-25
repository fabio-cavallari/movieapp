package com.example.movieapp.data.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class MovieDto(
    @JsonProperty("title") val title: String,
    @JsonProperty("poster_path") val poster: String,
    @JsonProperty("release_date") val date: String,
    @JsonProperty("vote_average") val rate: String,
)
