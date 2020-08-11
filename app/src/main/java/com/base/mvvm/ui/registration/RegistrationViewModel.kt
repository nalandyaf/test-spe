package com.base.mvvm.ui.registration

import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import com.base.mvvm.R
import com.base.mvvm.domain.entities.response.RegistrationResponse
import com.base.mvvm.domain.exceptions.MapperException
import com.base.mvvm.domain.usecases.user.IUserUsecases
import com.base.mvvm.ui.base.BaseViewModel
import com.base.mvvm.utils.AndroidUtils
import com.base.mvvm.utils.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RegistrationViewModel(baseUsecase: IUserUsecases, schedulerProvider: SchedulerProvider) : BaseViewModel<IUserUsecases?, RegistrationNavigator?>(baseUsecase, schedulerProvider) {
    var username = ObservableField<String>()
    var email = ObservableField<String>()
    var password = ObservableField<String>()


    override fun defineLayout() {
        positiveButton.set(AndroidUtils.getString(R.string.registration_page_positive_button))
    }

    fun registration() {
        isLoading(true)
        try {
            compositeDisposable.add(baseUsecase!!.registration(email.get(), username.get(), password.get())
            !!.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ response: RegistrationResponse? -> this.onSuccess(response) }) { throwable: Throwable? -> onError(throwable!!) })
        } catch (e: MapperException) {
            e.printStackTrace()
            onError(e)
        }
    }

    private fun onSuccess(response: RegistrationResponse?) {
        Log.d(this.javaClass.name, "Token: " + response?.jwt)
    }
}