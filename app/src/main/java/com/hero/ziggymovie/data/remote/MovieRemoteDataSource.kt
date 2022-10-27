package com.hero.ziggymovie.data.remote

import com.hero.ziggymovie.data.api.MovieApi
import com.hero.ziggymovie.data.model.MovieListResponse
import io.reactivex.Single

interface MovieRemoteDataSource {
    fun getMovieList(keyword: String): Single<MovieListResponse>
}

class MovieRemoteDataSourceImpl(
    private val movieApi: MovieApi
) : MovieRemoteDataSource {
    override fun getMovieList(keyword: String): Single<MovieListResponse> {
        return movieApi.getMovieList(
            MovieApi.CLIENT_ID,
            MovieApi.CLIENT_SECRET_KEY,
            keyword)
    }
}