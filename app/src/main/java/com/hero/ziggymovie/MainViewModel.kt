package com.hero.ziggymovie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hero.ziggymovie.data.repository.MovieRepository
import com.hero.ziggymovie.view.model.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>>
        get() = _movieList

    fun searchMovie(keyword: String) {
        compositeDisposable.add(movieRepository.getMovieList(keyword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _movieList.value = it
                    Log.d("mainViewModel", "searchMovie: " + "$it")
                },
                {
                    Log.e("mainViewModel", "searchMovie: " + "$it", )
                }
            )
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

//    val moviePagedList : LiveData<PagingData<MovieData>> by lazy {
//        moviePagedListRepository.fetchLiveMoviePagedList(compositeDisposable)
//    }
}