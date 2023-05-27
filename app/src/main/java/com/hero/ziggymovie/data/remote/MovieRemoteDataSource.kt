package com.hero.ziggymovie.data.remote

import com.hero.ziggymovie.data.api.MovieApi
import com.hero.ziggymovie.data.model.MovieListResponse
import io.reactivex.Single
import javax.inject.Inject

interface MovieRemoteDataSource {
    fun getMovieList(page: Int, keyword: String): Single<MovieListResponse>
}

class MovieRemoteDataSourceImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieRemoteDataSource {
    override fun getMovieList(page: Int, keyword: String): Single<MovieListResponse> {
        return movieApi.getMovieList(page)
    }
}