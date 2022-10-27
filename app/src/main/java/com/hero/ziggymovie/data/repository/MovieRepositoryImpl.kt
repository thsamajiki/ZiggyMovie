package com.hero.ziggymovie.data.repository

import com.hero.ziggymovie.data.mapper.toEntity
import com.hero.ziggymovie.data.remote.MovieRemoteDataSource
import com.hero.ziggymovie.view.model.Movie
import io.reactivex.Single

class MovieRepositoryImpl(
    private val remoteDataSource: MovieRemoteDataSource
): MovieRepository {
    override fun getMovieList(keyword: String): Single<List<Movie>> {
        return remoteDataSource.getMovieList(keyword)
            .map {
                it.movieList.map {
                    it.toEntity()
                }
            }
    }
}