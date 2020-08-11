package com.base.mvvm.ui.genre

import com.base.mvvm.domain.entities.Genre
import com.base.mvvm.ui.base.BaseNavigator

interface GenreNavigator : BaseNavigator {
    fun setDataSpinner(items: ArrayList<String>, genre: List<Genre>)
    fun hideLoading()
    fun toDetail(id: Int?)
    fun clearRecycler()
}