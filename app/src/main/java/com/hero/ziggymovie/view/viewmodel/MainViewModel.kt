package com.hero.ziggymovie.view.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.hero.ziggymovie.data.model.Movie
import com.hero.ziggymovie.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    // 아이템을 무한히 발행하거나 오랫동안 실행되는 Observable의 경우에는 제대로 종료해주지 않는다면 메모리 릭이 발생할 수 있다.
    // 더 이상 Observable의 구독이 필요하지 않을 때에는 이를 폐기(dispose)하는 것이 효율적이다.
    private val compositeDisposable = CompositeDisposable()

    // 내부에서 설정하는 자료형은 private 뮤터블로 & 변수명 앞에 언더바
    // 변경가능하도록 설정
    private val _movieList = MutableLiveData<PagingData<Movie>>()
    // 페이징이란 데이터를 가져올 때 한 번에 모든 데이터를 가져오는 것이 아니라 일정한 덩어리로 나눠서 가져오는 것을 뜻한다.
    // 예를 들어, 구글에서 어떤 키워드로 검색하게 되면 모든 데이터를 한 번에 가져오는 것이 아니라 10페이지씩 데이터를 가져오게 된다.
    // 페이징을 사용하면 성능, 메모리, 비용 측면에서 굉장히 효율적이다.


    // 외부에서는 언더바 없이 변수 이름 설정 & mutable이 아닌 그냥 livedata로 값 변경 x & public
    // get 할 때 mutableLiveData를 반환하도록 설정
    val movieList: LiveData<PagingData<Movie>>
        get() = _movieList

    val keyword: MutableLiveData<String> = MutableLiveData()

    private val searchSubject = PublishSubject.create<String>() // 구독한 시점부터 데이터를 새로 받아오는 Subject

    private val keywordObserver: (String) -> Unit = { keyword ->
        searchSubject.onNext(keyword) // onNext를 통해 검색어 추가
    }

    init {
        keyword.observeForever(keywordObserver)

        compositeDisposable.add(
            searchSubject
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .subscribe(
                    {   keyword ->
                        searchMovie(keyword)
                    },
                    {
                        it.printStackTrace()
                    }
                ) // subscribe()를 호출한 후에는 Disposable 객체가 반환된다.
        )
    }

    private fun searchMovie(keyword: String) {
        compositeDisposable.add(movieRepository.getMovieList(keyword)
            .subscribeOn(Schedulers.io())   // 호출 시점 상위에 해당하는 부분의 쓰레드를 설정
            .observeOn(AndroidSchedulers.mainThread())  // 호출시점 하위 스트림의 쓰레드를 설정
            .subscribe(
                {
                    _movieList.value = it
                    Log.d("mainViewModel", "searchMovie: $it")
                },
                {
                    Log.e("mainViewModel", "searchMovie: $it", )
                }
            )
        )
    }

    override fun onCleared() {
        compositeDisposable.clear() // 발행하는 Observable 아이템을 한꺼번에 폐기
        keyword.removeObserver(keywordObserver)
        super.onCleared()
    }
}