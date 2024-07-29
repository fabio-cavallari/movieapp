package com.example.movieapp.moviedetail.data.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class GenreDto(
    @JsonProperty("id") val id: Int,
    @JsonProperty("name") val name: String
)