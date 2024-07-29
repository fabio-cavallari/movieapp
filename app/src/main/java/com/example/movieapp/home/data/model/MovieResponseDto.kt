package com.example.movieapp.home.data.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class MovieResponseDto(
    @JsonProperty("page") val page: Int,
    @JsonProperty("results") val results: List<MovieDto>,
    @JsonProperty("total_pages") val totalPages: Int,
)
