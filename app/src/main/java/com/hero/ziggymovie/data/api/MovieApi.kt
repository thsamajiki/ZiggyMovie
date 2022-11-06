package com.hero.ziggymovie.data.api

import com.hero.ziggymovie.data.model.MovieListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MovieApi {

    @GET("/v1/search/movie.json")
    fun getMovieList(
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") clientSecret: String,
        @Query("query") keyword: String,
        @Query("start") start: Int,
        @Query("display") display: Int = 10
    ) : Single<MovieListResponse>

    companion object {
        const val BASE_URL = "https://openapi.naver.com"
        const val CLIENT_ID = "r2_QGtDthxd3rfdGTBL9"
        const val CLIENT_SECRET_KEY = "9SjkOsayZ3"
    }
}