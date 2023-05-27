package com.hero.ziggymovie.data.mapper

import com.hero.ziggymovie.data.model.Movie
import com.hero.ziggymovie.data.model.MovieResponse

private const val IMAGE_DOMAIN = "https://image.tmdb.org/t/p/original"

fun MovieResponse.toEntity(): Movie {
    return Movie(
        id = id,
        title = title.orEmpty(),
        overView = overview.orEmpty(),
        poster = IMAGE_DOMAIN + posterPath.orEmpty(),
        releaseDate = releaseDate.orEmpty(),
        voteAverage = voteAverage
    )
}


//fun MovieResponse.toEntity(): Movie {
//    return Movie(
//        title = title,
//        subtitle = subtitle,
//        webLink = link,
//        imageUrl = image,
//        publishedYear = pubDate,
//        directors = directors.split("|").filter { it.isNotEmpty() },
//        actors = actors.split("|").filter { it.isNotEmpty() },
//        userRating = userRating.toFloatOrNull() ?: 0f
//    )
//}