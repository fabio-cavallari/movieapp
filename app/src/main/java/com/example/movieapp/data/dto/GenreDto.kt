package com.example.movieapp.data.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class GenreDto(
    val id: Int,
    val name: String
)