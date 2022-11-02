package com.hero.ziggymovie.data.mapper

import com.hero.ziggymovie.data.model.MovieResponse
import com.hero.ziggymovie.data.model.Movie

// DB로부터 받아온 데이터 모델과 UI에 맞는 데이터 모델간의 변환을 해주는 역할
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