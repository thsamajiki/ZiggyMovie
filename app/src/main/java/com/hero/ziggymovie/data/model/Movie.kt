package com.hero.ziggymovie.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val overView: String,
    val poster: String,
    val releaseDate : String,
    val voteAverage: Double
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