package com.example.movieapp.data.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ProductionCompanyDto(
    @JsonProperty("id") val id: Int,
    @JsonProperty("logo_path") val logoPath: String?,
)