package com.base.mvvm.ui.movies.seeMore

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.base.mvvm.BR
import com.base.mvvm.R
import com.base.mvvm.ViewModelProviderFactory
import com.base.mvvm.databinding.ActivitySeeMoreBinding
import com.base.mvvm.ui.EndlessRecyclerOnScrollListener
import com.base.mvvm.ui.base.BaseActivity
import com.base.mvvm.ui.movies.detailMovie.DetailMovieActivity
import javax.inject.Inject

class SeeMoreActivity : BaseActivity<ActivitySeeMoreBinding, SeeMoreViewModel>(), SeeMoreNavigator {

    @JvmField
    @Inject
    var factory: ViewModelProviderFactory? = null
    var mBinding: ActivitySeeMoreBinding? = null

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_see_more
    override val viewModel: SeeMoreViewModel
        get() = ViewModelProvider(this, factory!!).get(SeeMoreViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
        mBinding = viewDataBinding
        mBinding!!.activity = this
        mBinding!!.list.addOnScrollListener(object : EndlessRecyclerOnScrollListener() {
            override fun onLoadMore() {
                viewModel.onLoadMore(intent!!.getIntExtra("data", 1))
            }
        })
        createLoading()
        viewModel.fetchData(intent!!.getIntExtra("data", 1))
    }

    override fun hideLoad() {
        dismissLoading()
    }

    override fun toDetail(id: Int?) {
        val intent = Intent(this, DetailMovieActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    override fun handleError(throwable: Throwable?) {

    }

    companion object {
        val TYPE_POPULAR: Int = 1
        val TYPE_UPCOMING: Int = 2
        val TYPE_TOP_RATED: Int = 3
    }
}