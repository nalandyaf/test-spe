package com.base.mvvm.ui.base

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.base.mvvm.utils.AndroidUtils
import com.base.mvvm.utils.SchedulerProvider
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

abstract class BaseViewModel<U, N : BaseNavigator?>(baseUsecase: U, schedulerProvider: SchedulerProvider) : ViewModel() {
    private var mNavigator: WeakReference<N>? = null
    protected val baseUsecase: U
    var compositeDisposable: CompositeDisposable
        protected set
    val schedulerProvider: SchedulerProvider
    var appBarTitle = ObservableField<String>()
    var showProgressBar = ObservableBoolean()
    var showAlertDialog = ObservableBoolean()
    var progressBarMessage = ObservableField<String?>()

    var positiveButton = ObservableField<String>()
    var negativeButton = ObservableField<String>()
    abstract fun defineLayout()
    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    val navigator: N?
        get() {
            return mNavigator!!.get()
        }


    fun setNavigator(navigator: N) {
        mNavigator = WeakReference(navigator)
    }

    fun isLoading(isLoading: Boolean) {
        showProgressBar.set(isLoading)
    }

    fun isLoading(isLoading: Boolean, message: Int) {
        showProgressBar.set(isLoading)
        progressBarMessage.set(AndroidUtils.getString(message))
    }

    protected fun onError(throwable: Throwable) {
        isLoading(false)
        navigator?.handleError(throwable)
        showAlertDialog.set(true)
        throwable.printStackTrace()
    }

    protected fun onSuccess(o: Any?) {}


    init {
        compositeDisposable = CompositeDisposable()
        this.schedulerProvider = schedulerProvider
        this.baseUsecase = baseUsecase
        defineLayout()
    }
}