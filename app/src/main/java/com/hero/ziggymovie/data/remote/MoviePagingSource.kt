package com.hero.ziggymovie.data.remote

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.hero.ziggymovie.data.model.MovieResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class MoviePagingSource(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val keyword: String
): RxPagingSource<Int, MovieResponse>() {

    override fun getRefreshKey(state: PagingState<Int, MovieResponse>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, MovieResponse>> {
        val page = params.key ?: 1

        val startPosition = params.loadSize * (page - 1) + 1

        return movieRemoteDataSource.getMovieList(
            keyword = keyword,
            loadSize = params.loadSize,
            startPosition = startPosition
        ).subscribeOn(Schedulers.io())
            .map<LoadResult<Int, MovieResponse>> { response ->
                LoadResult.Page(
                    data = response.movieList,
                    prevKey = null,
                    nextKey = page + 1
                )
            }
            .onErrorReturn { throwable ->
                throwable.printStackTrace()
                LoadResult.Error(throwable)
            }
    }
}