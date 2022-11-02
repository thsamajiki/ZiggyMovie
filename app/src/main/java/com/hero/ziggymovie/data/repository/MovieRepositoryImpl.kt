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

// Repository에서 Pager와 PagingSource를 사용하여 PagingData로 반환한다.
// PagingConfig로 페이저의 기본 설정을 정의해준 뒤 Pager 객체를 생성한다. 추가적으로 Pager 생성 시 초기 키 값을 지정해줄 수도 있다.
// Pager를 생성 후 Flow 형태로 반환한다. 반환 형태는 Observable, LiveData로도 가능하다.
class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource
): MovieRepository {
    override fun getMovieList(keyword: String): Flowable<PagingData<Movie>> {

        // PagingSource 객체 및 PagingConfig 객체를 바탕으로 반응형 스트림을 생성한다.
        // 각 PagingData는 페이징 된 데이터 스냅샷을 나타내며, Pager로부터 Flow, Observable, LiveData 형태를 반환.
        return Pager(
            // 페이징을 구성하는 방법을 정의함.
            // 페이징하는 데이터의 크기, 첫 페이징 데이터 크기 등 PagingSource를 구성하는 방법을 정의
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