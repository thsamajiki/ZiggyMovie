package com.hero.ziggymovie.data.repository

import androidx.paging.PagingData
import com.hero.ziggymovie.view.model.Movie
import io.reactivex.Flowable

interface MovieRepository {
    fun getMovieList(keyword: String): Flowable<PagingData<Movie>>
}