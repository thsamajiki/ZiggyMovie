package com.hero.ziggymovie.data.remote

import com.hero.ziggymovie.data.api.MovieApi
import com.hero.ziggymovie.data.model.MovieListResponse
import com.hero.ziggymovie.data.model.MovieResponse
import com.hero.ziggymovie.data.model.SearchMovieResponse
import io.reactivex.Single
import javax.inject.Inject

interface MovieRemoteDataSource {
    fun getMovieList(page: Int): MovieListResponse

    fun getMovieDetail(movieId: Int): MovieResponse

    fun searchMovie(page: Int, keyword: String) : Single<SearchMovieResponse>
}

class MovieRemoteDataSourceImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieRemoteDataSource {
    override fun getMovieList(page: Int): MovieListResponse {
        return movieApi.getMovieList(page)
    }

    override fun getMovieDetail(movieId: Int): MovieResponse {
        return movieApi.getMovieDetail(movieId)
    }

    override fun searchMovie(page: Int, keyword: String): Single<SearchMovieResponse> {
        return movieApi.searchMovie(page, keyword)
    }
}