package com.hero.ziggymovie.data.api

import com.hero.ziggymovie.data.model.MovieListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    fun getMovieList(
        @Query("page") page : Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language : String = "ko"
    ) : Single<MovieListResponse>

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        private const val API_KEY = "d99bfebe6e692708ef648145bc997704"
    }
}