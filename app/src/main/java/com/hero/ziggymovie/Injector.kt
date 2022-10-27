package com.hero.ziggymovie

import com.hero.ziggymovie.data.api.MovieApi
import com.hero.ziggymovie.data.remote.MovieRemoteDataSource
import com.hero.ziggymovie.data.remote.MovieRemoteDataSourceImpl
import com.hero.ziggymovie.data.repository.MovieRepository
import com.hero.ziggymovie.data.repository.MovieRepositoryImpl
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Injector {

    fun provideMovieRepository(): MovieRepository {
        return MovieRepositoryImpl(provideMovieRemoteDataSource())
    }

    private fun provideMovieRemoteDataSource(): MovieRemoteDataSource {
        return MovieRemoteDataSourceImpl(provideMovieApi())
    }

    private fun provideMovieApi(): MovieApi {
        return Retrofit.Builder()
            .baseUrl(MovieApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }
}