package com.example.movieapp.data.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class CollectionDto(
    val id: Long,
    val name: String,
    @JsonProperty("poster_path")
    val posterPath: String?,
)