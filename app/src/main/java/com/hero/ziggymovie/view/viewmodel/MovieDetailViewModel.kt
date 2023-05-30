package com.hero.ziggymovie.view.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hero.ziggymovie.data.model.MovieData
import com.hero.ziggymovie.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val movieRepository: MovieRepository
) : ViewModel() {

    companion object {
        const val KEY_MOVIE = "movie"
    }

    val movieData: MovieData = savedStateHandle.get<MovieData>(KEY_MOVIE)!!
}