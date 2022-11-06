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

    private val compositeDisposable = CompositeDisposable()

    private val _movieList = MutableLiveData<PagingData<Movie>>()

    val movieList: LiveData<PagingData<Movie>>
        get() = _movieList

    val keyword: MutableLiveData<String> = MutableLiveData()

    private val searchSubject = PublishSubject.create<String>()

    private val keywordObserver: (String) -> Unit = { keyword ->
        searchSubject.onNext(keyword)
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
                )
        )
    }

    private fun searchMovie(keyword: String) {
        compositeDisposable.add(movieRepository.getMovieList(keyword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
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
        compositeDisposable.clear()
        keyword.removeObserver(keywordObserver)
        super.onCleared()
    }
}