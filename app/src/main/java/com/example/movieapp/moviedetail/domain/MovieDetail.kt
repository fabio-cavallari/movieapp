package com.example.movieapp.moviedetail.domain

data class MovieDetail(
    val belongsToCollection: Collection? = null,
    val genres: List<Genre> = listOf(),
    val homepage: String = "",
    val id: Int = 0,
    val imdbId: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterPath: String = "",
    val productionCompanies: List<ProductionCompany> = listOf(),
    val releaseDate: String = "",
    val runtime: Int = 0,
    val tagline: String = "",
    val title: String = "",
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0
)

val movieDetailSample = MovieDetail(
    belongsToCollection = Collection(
        id = 1022790,
        name = "Inside Out Collection",
        posterPath = "/Apr19lGxP7gm6y2HQX0kqOXTtqC.jpg",
    ),

    genres = listOf(
        Genre(
            id = 16,
            name = "Animation"
        ), Genre(
            id = 10751,
            name = "Family"
        ), Genre(
            id = 12,
            name = "Adventure"
        )
    ),
    homepage = "https://movies.disney.com/inside-out-2",
    id = 1022789,
    imdbId = "tt22022452",
    overview = "Teenager Riley's mind headquarters is undergoing a sudden demolition to make room for something entirely unexpected: new Emotions! Joy, Sadness, Anger, Fear and Disgust, who’ve long been running a successful operation by all accounts, aren’t sure how to feel when Anxiety shows up. And it looks like she’s not alone.",
    popularity = 4659.516,
    posterPath = "/vpnVM9B6NMmQpWeZvzLvDESb2QY.jpg",
    productionCompanies = listOf(
        ProductionCompany(
            id = 2,
            logoPath = "/wdrCwmRnLFJhEoH8GSfymY85KHT.png",
        ), ProductionCompany(
            id = 3,
            logoPath = "/1TjvGVDMYsj6JBxOAkUHpPEwLf7.png",
        )
    ),
    releaseDate = "2024-06-11",
    runtime = 97,
    tagline = "Make room for new emotions.",
    title = "Inside Out 2",
    voteAverage = 7.63,
    voteCount = 2118

)
