package com.hero.ziggymovie.data.repository

import androidx.paging.PagingData
import com.hero.ziggymovie.data.model.MovieData
import com.hero.ziggymovie.data.model.MovieResponse
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovieList(page: Int): Flow<PagingData<MovieData>>

    fun getMovieDetail(movieId: Int): MovieResponse

    fun searchMovie(page: Int, keyword: String): Flowable<PagingData<MovieData>>
}