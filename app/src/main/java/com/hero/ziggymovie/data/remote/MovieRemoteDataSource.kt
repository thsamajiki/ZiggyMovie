package com.hero.ziggymovie.data.remote

import com.hero.ziggymovie.data.api.MovieApi
import com.hero.ziggymovie.data.model.MovieListResponse
import io.reactivex.Single
import javax.inject.Inject

interface MovieRemoteDataSource {
    fun getMovieList(keyword: String, loadSize: Int, startPosition: Int): Single<MovieListResponse>
}

class MovieRemoteDataSourceImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieRemoteDataSource {
    override fun getMovieList(keyword: String, loadSize: Int, startPosition: Int): Single<MovieListResponse> {
        return movieApi.getMovieList(
            MovieApi.CLIENT_ID,
            MovieApi.CLIENT_SECRET_KEY,
            keyword,
            display = loadSize,
            start = startPosition)
    }
}