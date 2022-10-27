package com.hero.ziggymovie.view.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

class MovieDetailViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

//    val movieDetail : LiveData<MovieDetail> by lazy {
//        movieDetailRepository.fetchMovieDetail(compositeDisposable, movieId)
//    }
//
//    val loadStates : LiveData<LoadStates> by lazy {
//        movieDetailRepository.getMovieDetailNetworkState()
//    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}