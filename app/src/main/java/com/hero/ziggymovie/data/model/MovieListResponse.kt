package com.hero.ziggymovie.data.model

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val movieList: List<MovieResponse>
)