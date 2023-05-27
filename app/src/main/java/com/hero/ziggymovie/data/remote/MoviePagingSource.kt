package com.hero.ziggymovie.data.remote

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.hero.ziggymovie.data.model.MovieResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

// 영화 데이터 소스를 식별하기 위해 PagingSource 구현을 정의
class MoviePagingSource(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val keyword: String
): RxPagingSource<Int, MovieResponse>() {

    // 초기 key 값, 또는 데이터 로드 중단 후 재로드시 이전 position 에서 중단된 key 값을 가져오는 등 load()에서 사용할 key 값을 가져오는 로직을 구현
    // 스와이프 Refresh 나 데이터 업데이트 등으로 현재 목록을 대체할 새 데이터를 로드할 때 사용된다.
    // scroll 마지막 위치에서 paging 이 정상적으로 이루어지도록 가장 최근에 접근한 인덱스인 anchorPosition 으로 주변 데이터를 다시 로드한다.
    // previousKey 가 null 이면 첫번째 페이지를 반환하고 nextKey 가 null 이면 마지막 페이지를 반환한다.
    // 만약 둘 다 null 이면 null 을 반환
    override fun getRefreshKey(state: PagingState<Int, MovieResponse>): Int? {
        // let()은 함수를 호출하는 객체를 이어지는 블록의 인자로 넘기고, 블록의 결과값을 반환
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    // 영화 데이터 소스에서 페이징 된 데이터를 검색하는 방법을 나타내기 위해 재정의
    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, MovieResponse>> {
        val page = params.key ?: 1

        val startPosition = params.loadSize * (page - 1) + 1

        return movieRemoteDataSource.getMovieList(
            page = page,
            keyword = keyword
        ).subscribeOn(Schedulers.io())
            .map<LoadResult<Int, MovieResponse>> { response ->
                LoadResult.Page(
                    data = response.movieList,
                    prevKey = null, // Only paging forward.
                    nextKey = page + 1
                )
            }
            .onErrorReturn { throwable ->
                throwable.printStackTrace()
                LoadResult.Error(throwable)
            }
    }
}