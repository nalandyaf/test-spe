package com.base.mvvm.ui.about

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.base.mvvm.BR
import com.base.mvvm.R
import com.base.mvvm.ViewModelProviderFactory
import com.base.mvvm.databinding.FragmentAboutBinding
import com.base.mvvm.ui.base.BaseFragment
import com.base.mvvm.ui.movies.MoviesViewModel
import javax.inject.Inject

class AboutFragment : BaseFragment<FragmentAboutBinding, MoviesViewModel>() {

    @JvmField
    @Inject
    var factory: ViewModelProviderFactory? = null

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_about
    override val viewModel: MoviesViewModel
        get() = ViewModelProvider(this, factory!!).get(MoviesViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}