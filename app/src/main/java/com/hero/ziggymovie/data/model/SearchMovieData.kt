package com.hero.ziggymovie.data.model

data class SearchMovieData(
    val page: Int,
    val results: List<MovieData>,
    val totalPages: Int,
    val totalResults: Int
)