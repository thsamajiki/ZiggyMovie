package com.hero.ziggymovie.data.repository

class LoadStates(val status : LoadStatus, val msg: String) {

    companion object {
        val LOADED: LoadStates
        val LOADING: LoadStates
        val ERROR: LoadStates
        val ENDOFLIST: LoadStates

        init {
            LOADED = LoadStates(LoadStatus.SUCCESS, "Success")
            LOADING = LoadStates(LoadStatus.RUNNING, "Running")
            ERROR = LoadStates(LoadStatus.FAILED, "Error")
            ENDOFLIST = LoadStates(LoadStatus.FAILED, "Reached the end")
        }
    }
}