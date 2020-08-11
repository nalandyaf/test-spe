package com.base.mvvm.ui.movies.detailMovie

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.base.mvvm.BR
import com.base.mvvm.R
import com.base.mvvm.ViewModelProviderFactory
import com.base.mvvm.databinding.ActivityDetailMovieBinding
import com.base.mvvm.ui.EndlessRecyclerOnScrollListener
import com.base.mvvm.ui.base.BaseActivity
import com.pierfrancescosoffritti.youtubeplayer.player.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerFullScreenListener
import javax.inject.Inject

class DetailMovieActivity : BaseActivity<ActivityDetailMovieBinding, DetailMovieViewModel>(), DetailMovieNavigator {

    @JvmField
    @Inject
    var factory: ViewModelProviderFactory? = null
    var mBinding: ActivityDetailMovieBinding? = null


    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_detail_movie
    override val viewModel: DetailMovieViewModel
        get() = ViewModelProvider(this, factory!!).get(DetailMovieViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = viewDataBinding
        mBinding!!.acivity = this
        mBinding!!.list.addOnScrollListener(object : EndlessRecyclerOnScrollListener() {
            override fun onLoadMore() {
                viewModel.onLoadMore(intent!!.getIntExtra("data", 1))
            }
        })
        viewModel.setNavigator(this)
        createLoading()
        viewModel.fetchData(intent.getIntExtra("id", 1));
    }

    override fun hideLoading() {
        dismissLoading()
    }

    override fun setupPlayer(key: String?) {
        mBinding!!.player.initialize({ initializedYouTubePlayer ->
            initializedYouTubePlayer.addListener(object : AbstractYouTubePlayerListener() {
                override fun onReady() {
                    initializedYouTubePlayer.cueVideo(key, 0f)
                }
            })
        }, true)

        mBinding!!.player.addFullScreenListener(object : YouTubePlayerFullScreenListener {
            override fun onYouTubePlayerEnterFullScreen() {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            }

            override fun onYouTubePlayerExitFullScreen() {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
        })
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mBinding!!.player.enterFullScreen()
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            mBinding!!.player.exitFullScreen()
        }
    }

    override fun handleError(throwable: Throwable?) {

    }
}