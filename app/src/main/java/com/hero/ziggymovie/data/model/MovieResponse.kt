package com.hero.ziggymovie.data.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("title") // JSON에서 데이터에 매칭되는 이름 명시
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