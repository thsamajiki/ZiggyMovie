package com.hero.ziggymovie.data.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("title")
    val title: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("pubDate")
    val pubDate: String,
    @SerializedName("director")
    val directors: String,
    @SerializedName("actor")
    val actors: String,
    @SerializedName("userRating")
    val userRating: String
)