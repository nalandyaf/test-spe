package com.base.mvvm.ui.base

import android.annotation.TargetApi
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.base.mvvm.R
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog
import dagger.android.AndroidInjection
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

abstract class BaseActivity<T : ViewDataBinding?, V : BaseViewModel<*, *>?> : AppCompatActivity(), BaseFragment.Callback {
    var viewDataBinding: T? = null
        private set
    private var mViewModel: V? = null
    abstract val bindingVariable: Int
    @get:LayoutRes
    abstract val layoutId: Int

    abstract val viewModel: V

    protected lateinit var dialog: SweetAlertDialog

    override fun onFragmentAttached() {}
    override fun onFragmentDetached(tag: String?) {}
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun hasPermission(permission: String?): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }

    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView<T>(this, layoutId)
        mViewModel = if (mViewModel == null) viewModel else mViewModel
        viewDataBinding!!.setVariable(bindingVariable, mViewModel)
        viewDataBinding!!.executePendingBindings()
    }

    fun createLoading() {
        dialog = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
        loading(dialog)
    }

    fun dismissLoading(){
        dialog.dismissWithAnimation()
    }

    fun loading(dialog: SweetAlertDialog): SweetAlertDialog {
        dialog.progressHelper.barColor = R.color.colorPrimary
        dialog.progressHelper.rimColor = R.color.colorAccent
        dialog.titleText = "Loading"
        dialog.setCancelable(false)
        if (!dialog.isShowing) {
            dialog.show()
        } else {
            dialog.dismissWithAnimation()
            //dialog.show()
        }
        return dialog
    }
}