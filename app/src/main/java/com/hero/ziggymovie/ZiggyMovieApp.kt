package com.hero.ziggymovie

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ZiggyMovieApp : Application() {
    companion object {
        lateinit var instance : ZiggyMovieApp

        fun getInstance() : Application {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
    }
}