package com.hero.ziggymovie.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val title: String,
    val subtitle: String,
    val webLink: String,
    val imageUrl: String,
    val publishedYear: String,
    val directors: List<String>,
    val actors: List<String>,
    val userRating: Float
) : Parcelable