package com.hero.ziggymovie.di

import com.hero.ziggymovie.data.api.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun bindMovieApi(): MovieApi =
        Retrofit.Builder()
            .baseUrl(MovieApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // JSON을 Gson으로 파싱하기 위해 GsonConverterFactory.create()로 GsonConverter를 가져온다.
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build() //Retrofit 객체 생성
            .create(MovieApi::class.java)
}