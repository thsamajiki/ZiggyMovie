package com.hero.ziggymovie.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import androidx.paging.rxjava2.flowable
import com.hero.ziggymovie.data.mapper.toEntity
import com.hero.ziggymovie.data.model.MovieData
import com.hero.ziggymovie.data.model.MovieResponse
import com.hero.ziggymovie.data.remote.MoviePagingSource
import com.hero.ziggymovie.data.remote.MovieRemoteDataSource
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource
): MovieRepository {
    override fun getMovieList(page: Int): Flow<PagingData<MovieData>> {
        return Pager(
            config = PagingConfig(
                pageSize = 12, // 각 페이지에 로드할 데이터 수
                initialLoadSize = 12
            ),
            pagingSourceFactory = {
                MoviePagingSource(remoteDataSource, "") // PagingSource 인스턴스를 생성
            }
        )
            .flow
            .map {
                it.map { movieResponse -> movieResponse.toEntity() }
            }
    }

    override fun getMovieDetail(movieId: Int): MovieResponse {
        return remoteDataSource.getMovieDetail(movieId)
    }

    override fun searchMovie(page: Int, keyword: String): Flowable<PagingData<MovieData>> {
        return Pager(
            config = PagingConfig(
                pageSize = 12, // 각 페이지에 로드할 데이터 수
                initialLoadSize = 12
            ),
            pagingSourceFactory = {
                MoviePagingSource(remoteDataSource, keyword) // PagingSource 인스턴스를 생성
            }
        )
            .flowable // Pager를 생성 후 Flowable 형태로 반환.
            .map {
                it.map { movieResponse -> movieResponse.toEntity() }
            }
    }
}