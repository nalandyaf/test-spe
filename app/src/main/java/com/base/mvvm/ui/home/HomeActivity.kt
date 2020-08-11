package com.base.mvvm.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.base.mvvm.BR
import com.base.mvvm.R
import com.base.mvvm.ViewModelProviderFactory
import com.base.mvvm.databinding.ActivityHomeBinding
import com.base.mvvm.domain.models.ContentTab
import com.base.mvvm.ui.base.BaseActivity
import com.base.mvvm.ui.genre.GenreFragment
import com.base.mvvm.ui.movies.MoviesFragment
import com.google.android.material.tabs.TabLayout
import javax.inject.Inject

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(), HomeNavigator {

    @JvmField
    @Inject
    var factory: ViewModelProviderFactory? = null
    var activityHomeBinding: ActivityHomeBinding? = null
    var contentTab: ArrayList<ContentTab>? = null

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_home
    override val viewModel: HomeViewModel
        get() = ViewModelProvider(this, factory!!).get(HomeViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
        activityHomeBinding = viewDataBinding
        listTab()
        setTab()
    }

    private fun setTab() {
        viewDataBinding!!.viewPager.adapter = HomeMenuAdapter(supportFragmentManager, contentTab!!)
        viewDataBinding!!.bottomNavigation.setupWithViewPager(viewDataBinding!!.viewPager)
        viewDataBinding!!.viewPager.offscreenPageLimit = 2
        setTabIcon(viewDataBinding!!.bottomNavigation)
    }

    private fun setTabIcon(bottomNavigation: TabLayout?) {
        for (pos in 0 until contentTab!!.size) {
            val t = viewDataBinding!!.bottomNavigation.getTabAt(pos)
            t!!.customView = getTabView(pos)
            viewDataBinding!!.bottomNavigation.getTabAt(pos)!!.setIcon(contentTab!![pos].imageId)
        }
    }

    private fun getTabView(pos: Int): View {
        val v = LayoutInflater.from(this).inflate(R.layout.custom_tab, null)
        val tv = v.findViewById<TextView>(R.id.text)
        tv.text = contentTab!![pos].title
        val img = v.findViewById<ImageView>(R.id.image)
        img.setImageResource(contentTab!![pos].imageId)
        return v
    }

    private fun listTab(): ArrayList<ContentTab> {
        contentTab = java.util.ArrayList()
        contentTab!!.add(makeTab(MoviesFragment(), "Movies", R.drawable.icon_movies))
        contentTab!!.add(makeTab(GenreFragment(), "Genre", R.drawable.icon_genre))
        return contentTab!!
    }

    private fun makeTab(fragment: Fragment, title: String, imageId: Int): ContentTab {
        return ContentTab().setFragment(fragment).setImageId(imageId).setTitle(title)
    }

    override fun handleError(throwable: Throwable?) {

    }

}