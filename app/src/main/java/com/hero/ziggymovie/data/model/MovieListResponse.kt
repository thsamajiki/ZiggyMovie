package com.hero.ziggymovie.data.model

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("lastBuildDate")
    val lastBuildDate: String,
    @SerializedName("total")
    val total: Int,
    @SerializedName("start")
    val start: Int,
    @SerializedName("display")
    val display: Int,
    @SerializedName("items")
    val movieList: List<MovieResponse>
) {
}