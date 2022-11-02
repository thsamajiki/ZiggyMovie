package com.hero.ziggymovie.view.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hero.ziggymovie.data.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle  // ViewModel로 전달된 저장 상태에 대한 핸들
) : ViewModel() {

    companion object {
        const val KEY_MOVIE = "movie"
    }

    val movie: Movie = savedStateHandle.get<Movie>(KEY_MOVIE)!! // Activity/Fragment 간 이동시 전달되는 데이터를 관리하기 수월
}