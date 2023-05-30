package com.hero.ziggymovie.data.model

import com.google.gson.annotations.SerializedName

data class SearchMovieResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val result: List<MovieResponse>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)