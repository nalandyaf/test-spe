package com.base.mvvm.common

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.base.mvvm.R
import com.squareup.picasso.Picasso

object CommonSetter {
    @BindingAdapter("imageSource")
    fun imageSource(view: ImageView, imageSource: Int) {
        view.setImageResource(imageSource)
    }

    @kotlin.jvm.JvmStatic
    @BindingAdapter("isVisible")
    fun setIsVisible(view: View, isVisible: Boolean?) {
        if (isVisible == null) {
            return
        }
        view.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun imageSourceUrl(view: ImageView, url: String) {
        if (url.isNullOrEmpty()) {
            Picasso.get().load(R.drawable.photo_male_2).into(view)
        } else {
            Picasso.get().load(url).into(view)
        }
    }
}