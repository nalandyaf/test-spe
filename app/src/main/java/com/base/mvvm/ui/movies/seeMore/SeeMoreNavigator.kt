package com.base.mvvm.ui.movies.seeMore

import com.base.mvvm.ui.base.BaseNavigator

interface SeeMoreNavigator : BaseNavigator {
    fun hideLoad()
    fun toDetail(id: Int?)
}