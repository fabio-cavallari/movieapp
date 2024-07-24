package com.example.movieapp.data.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class MovieResponseDto(
    @JsonProperty("page") val page: Int,
    @JsonProperty("results") val results: List<MovieDto>,
    @JsonProperty("total_pages") val totalPages: Int,
)
