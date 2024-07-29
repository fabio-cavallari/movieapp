package com.example.movieapp.model

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

val movieSample = Movie(
    id = 1,
    title = "Inside Out 2",
    date = "2024-06-11",
    rate = 7.645,
    poster = "/vpnVM9B6NMmQpWeZvzLvDESb2QY.jpg"
)

val movieSample2 = Movie(
    id = 2,
    title = "Furiosa: A Mad Max Saga",
    date = "2024-06-11",
    rate = 7.645,
    poster = "/iADOJ8Zymht2JPMoy3R7xceZprc.jpg"
)

val movieSample3 = Movie(
    id = 3,
    title = "Kingdom of the Planet of the Apes",
    date = "2024-06-11",
    rate = 7.645,
    poster = "/gKkl37BQuKTanygYQG1pyYgLVgf.jpg"
)

val movieListSample = linkedSetOf(movieSample, movieSample2, movieSample3, movieSample, movieSample2, movieSample3)