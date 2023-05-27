package com.hero.ziggymovie.view.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.hero.ziggymovie.data.model.Movie
import com.hero.ziggymovie.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.launch
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
        requestMovieList()

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

    private fun requestMovieList() {
        viewModelScope.launch {
            kotlin.runCatching {
                movieRepository.getMovieList(
                    page = 1,
                    keyword = ""
                )
            }
                .map {
                    it.map {
                        it.map {
                            it
                        }
                    }
                }
                .onSuccess {
//                    _movieList.value = it
                }
                .onFailure {
                    Log.e("MainViewModel", "requestMovieList: $it")
                }
        }
    }

    private fun searchMovie(keyword: String) {
        compositeDisposable.add(movieRepository.getMovieList(page = 1, keyword = keyword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _movieList.value = it
                    Log.d("mainViewModel-success", "searchMovie: $it")
                },
                {
                    it.printStackTrace()
                    Log.e("mainViewModel-error", "searchMovie: $it")
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