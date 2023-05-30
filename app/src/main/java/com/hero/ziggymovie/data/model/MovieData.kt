package com.hero.ziggymovie.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieData(
    val id: Int,
    val adult: Boolean,
    val backdropPath: String?,
    val genreIds: List<Int>?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
) : Parcelable


//@Parcelize
//data class Movie(
//    val title: String,
//    val subtitle: String,
//    val webLink: String,
//    val imageUrl: String,
//    val publishedYear: String,
//    val directors: List<String>,
//    val actors: List<String>,
//    val userRating: Float
//) : Parcelable