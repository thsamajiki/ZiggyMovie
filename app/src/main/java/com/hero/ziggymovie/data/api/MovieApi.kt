package com.hero.ziggymovie.data.api

import com.hero.ziggymovie.data.model.MovieListResponse
import com.hero.ziggymovie.data.model.MovieResponse
import com.hero.ziggymovie.data.model.SearchMovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    fun getMovieList(
        @Query("page") page : Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language : String = "ko"
    ) : MovieListResponse

    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language : String = "ko"
    ) : MovieResponse

    @GET("search/movie")
    fun searchMovie(
        @Query("page") page : Int,
        @Query("query") query: String,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language : String = "ko"
    ): Single<SearchMovieResponse>

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        private const val API_KEY = "d99bfebe6e692708ef648145bc997704"
    }
}