package com.base.mvvm.ui.home

import com.base.mvvm.ui.base.BaseViewModel
import com.base.mvvm.utils.SchedulerProvider

class HomeViewModel(baseUsecase: Any?, schedulerProvider: SchedulerProvider)
    : BaseViewModel<Any?, HomeNavigator>(baseUsecase, schedulerProvider) {

    override fun defineLayout() {

    }
}