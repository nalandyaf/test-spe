package com.base.mvvm.ui.movies

import com.base.mvvm.ui.base.BaseNavigator

interface MoviesNavigator : BaseNavigator {
    fun hideShimmer()
    fun seeMorePopular()
    fun seeMoreTopRated()
    fun seeMoreUpcoming()
    fun toDetail(id: Int?)
}