package com.example.movieapp.moviedetail.data.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ProductionCompanyDto(
    @JsonProperty("id") val id: Int,
    @JsonProperty("logo_path") val logoPath: String?,
)