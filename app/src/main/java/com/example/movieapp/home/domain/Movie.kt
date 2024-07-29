package com.example.movieapp.home.domain

data class Movie(
    val id: Int,
    val title: String,
    val date: String,
    val poster: String,
    val rate: Double
) {
    //there were some cases where the api returns the same movie twice but with different rate
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Movie) return false
        return id == other.id
    }
    override fun hashCode(): Int {
        return id.hashCode()
    }
}