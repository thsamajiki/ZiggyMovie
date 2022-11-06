package com.hero.ziggymovie.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import androidx.paging.rxjava2.flowable
import com.hero.ziggymovie.data.mapper.toEntity
import com.hero.ziggymovie.data.model.Movie
import com.hero.ziggymovie.data.remote.MoviePagingSource
import com.hero.ziggymovie.data.remote.MovieRemoteDataSource
import io.reactivex.Flowable
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource
): MovieRepository {
    override fun getMovieList(keyword: String): Flowable<PagingData<Movie>> {

        return Pager(
            config = PagingConfig(
                pageSize = 12,
                initialLoadSize = 12
            ),
            pagingSourceFactory = {
                MoviePagingSource(remoteDataSource, keyword)
            }
        )
            .flowable
            .map {
                it.map { movieResponse -> movieResponse.toEntity() }
            }
    }
}