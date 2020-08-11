package com.base.mvvm.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.base.mvvm.R

object FragmentUtils {
    @JvmStatic
    fun showFragmentAndClearBackStack(fragment: Fragment?,
                                      fragmentManager: FragmentManager?) {
        if (fragmentManager == null) {
            return
        }
        if (fragmentManager.backStackEntryCount > 0) {
            val rootEntry = fragmentManager.getBackStackEntryAt(0)
            fragmentManager.popBackStackImmediate(rootEntry.id,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE)
            fragmentManager.beginTransaction().setReorderingAllowed(true).replace(R.id.fragment_container, fragment!!).commitNow()
        } else {
            fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            fragmentManager.beginTransaction().setReorderingAllowed(true).replace(R.id.fragment_container, fragment!!).commit()
        }
    }

    @JvmStatic
    fun showFragmentAndAddToBackStack(fragment: Fragment?,
                                      fragmentManager: FragmentManager,
                                      animated: Boolean) {
        val transaction = fragmentManager.beginTransaction()
        if (animated) {
            transaction.setCustomAnimations(R.anim.fragment_fade_in_forward,
                    R.anim.fragment_fade_out_forward,
                    R.anim.fragment_fade_in_back,
                    R.anim.fragment_fade_out_back)
        }
        transaction.replace(R.id.fragment_container, fragment!!)
        transaction.addToBackStack("backstack")
        transaction.commit()
    }

    fun addFragmentToBackStack(fragment: Fragment?,
                               fragmentManager: FragmentManager,
                               animated: Boolean) {
        val transaction = fragmentManager.beginTransaction()
        if (animated) {
            transaction.setCustomAnimations(R.anim.fragment_fade_in_forward,
                    R.anim.fragment_fade_out_forward,
                    R.anim.fragment_fade_in_back,
                    R.anim.fragment_fade_out_back)
        }
        transaction.replace(R.id.fragment_container, fragment!!)
        transaction.addToBackStack("backstack")
        transaction.commit()
    }
}