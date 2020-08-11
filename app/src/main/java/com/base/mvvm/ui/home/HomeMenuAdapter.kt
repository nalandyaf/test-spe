package com.base.mvvm.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.base.mvvm.domain.models.ContentTab
import java.util.*

class HomeMenuAdapter(fm: FragmentManager, private val modelTabs: ArrayList<ContentTab>) : FragmentStatePagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        return modelTabs[position].fragment!!
    }


    override fun getCount(): Int {
        return modelTabs.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return modelTabs[position].title
    }
}