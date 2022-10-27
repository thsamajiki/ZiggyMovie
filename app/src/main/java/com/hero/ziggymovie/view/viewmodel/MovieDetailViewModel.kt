package com.hero.ziggymovie.view.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hero.ziggymovie.view.model.Movie

class MovieDetailViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        const val KEY_MOVIE = "movie"
    }

    val movie: Movie = savedStateHandle.get<Movie>(KEY_MOVIE)!!
}