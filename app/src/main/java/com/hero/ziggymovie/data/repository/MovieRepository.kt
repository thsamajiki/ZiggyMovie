package com.hero.ziggymovie.data.repository

import androidx.paging.PagingData
import com.hero.ziggymovie.data.model.Movie
import io.reactivex.Flowable

interface MovieRepository {
    fun getMovieList(page: Int, keyword: String): Flowable<PagingData<Movie>>
}