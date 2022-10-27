package com.hero.ziggymovie.data.repository

import com.hero.ziggymovie.view.model.Movie
import io.reactivex.Single

interface MovieRepository {
    fun getMovieList(keyword: String): Single<List<Movie>>
}