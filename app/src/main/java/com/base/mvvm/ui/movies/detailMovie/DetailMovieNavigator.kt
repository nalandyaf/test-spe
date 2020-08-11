package com.base.mvvm.ui.movies.detailMovie

import com.base.mvvm.ui.base.BaseNavigator

interface DetailMovieNavigator : BaseNavigator {
    fun hideLoading()
    fun setupPlayer(key: String?)
}