package com.hero.ziggymovie.data.mapper

import com.hero.ziggymovie.data.model.MovieData
import com.hero.ziggymovie.data.model.MovieResponse

private const val IMAGE_DOMAIN = "https://image.tmdb.org/t/p/original"

fun MovieResponse.toEntity(): MovieData {
    return MovieData(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        genreIds = genreIds,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview.orEmpty(),
        popularity = popularity,
        posterPath = IMAGE_DOMAIN + posterPath.orEmpty(),
        releaseDate = releaseDate.orEmpty(),
        title = title.orEmpty(),
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}