package com.hero.ziggymovie.data.mapper

import com.hero.ziggymovie.data.model.MovieResponse
import com.hero.ziggymovie.view.model.Movie

fun MovieResponse.toEntity(): Movie {
    return Movie(
        title = title,
        subtitle = subtitle,
        webLink = link,
        imageUrl = image,
        publishedYear = pubDate,
        directors = directors.split("|").filter { it.isNotEmpty() },
        actors = actors.split("|").filter { it.isNotEmpty() },
        userRating = userRating.toFloatOrNull() ?: 0f
    )
}