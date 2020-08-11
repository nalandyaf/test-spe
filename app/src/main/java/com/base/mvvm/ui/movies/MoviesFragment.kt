package com.base.mvvm.ui.movies

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.base.mvvm.BR
import com.base.mvvm.R
import com.base.mvvm.ViewModelProviderFactory
import com.base.mvvm.databinding.FragmentMoviesBinding
import com.base.mvvm.ui.base.BaseFragment
import com.base.mvvm.ui.movies.detailMovie.DetailMovieActivity
import com.base.mvvm.ui.movies.seeMore.SeeMoreActivity
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import javax.inject.Inject

class MoviesFragment : BaseFragment<FragmentMoviesBinding, MoviesViewModel>(), MoviesNavigator {

    @JvmField
    @Inject
    var factory: ViewModelProviderFactory? = null
    var mBinding: FragmentMoviesBinding? = null

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_movies
    override val viewModel: MoviesViewModel
        get() = ViewModelProvider(this, factory!!).get(MoviesViewModel::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = viewDataBinding
        viewModel.setNavigator(this)
        createLoading()
        viewModel.fetchData()
        mBinding!!.popularMovieList.enableViewScaling(true)
        val snapHelper = GravitySnapHelper(Gravity.START)
        snapHelper.attachToRecyclerView(mBinding!!.topRatedList)
        snapHelper.attachToRecyclerView(mBinding!!.upcomingList)

    }

    private fun showShimmer() {
        mBinding!!.popularMovieList.showShimmerAdapter()
        mBinding!!.topRatedList.showShimmerAdapter()
        mBinding!!.upcomingList.showShimmerAdapter()
    }

    override fun hideShimmer() {
        dismissLoading()
    }

    override fun seeMorePopular() {
        val intent = Intent(context, SeeMoreActivity::class.java)
        intent.putExtra("data", SeeMoreActivity.TYPE_POPULAR)
        context?.startActivity(intent)
    }

    override fun seeMoreTopRated() {
        val intent = Intent(context, SeeMoreActivity::class.java)
        intent.putExtra("data", SeeMoreActivity.TYPE_TOP_RATED)
        context?.startActivity(intent)
    }

    override fun seeMoreUpcoming() {
        val intent = Intent(context, SeeMoreActivity::class.java)
        intent.putExtra("data", SeeMoreActivity.TYPE_UPCOMING)
        context?.startActivity(intent)
    }

    override fun toDetail(id: Int?) {
        val intent = Intent(context, DetailMovieActivity::class.java)
        intent.putExtra("id", id)
        context?.startActivity(intent)
    }

    override fun handleError(throwable: Throwable?) {

    }


}