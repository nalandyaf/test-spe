package com.base.mvvm.ui.movies.detailMovie.adapter

import androidx.databinding.ObservableField
import com.base.mvvm.databinding.AdapterTrailerBinding
import com.base.mvvm.domain.models.MovieVideos
import com.squareup.picasso.Picasso
import java.util.*

class ItemTrailerViewModel(itemData: MovieVideos?,
                           var actionSelected: (String) -> Unit,
                           var binding: AdapterTrailerBinding) : Observable() {

    var data: MovieVideos? = itemData
    var imageUrl = ObservableField<String>()

    init {
        imageUrl.set("https://img.youtube.com/vi/${data?.key}/hqdefault.jpg")
    }


    fun onSelected() {
        with(binding) {
            executePendingBindings()
        }
        actionSelected(data!!.key)
    }
}