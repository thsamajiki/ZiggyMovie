package com.hero.ziggymovie.view.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hero.ziggymovie.data.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        const val KEY_MOVIE = "movie"
    }

    val movie: Movie = savedStateHandle.get<Movie>(KEY_MOVIE)!!
}