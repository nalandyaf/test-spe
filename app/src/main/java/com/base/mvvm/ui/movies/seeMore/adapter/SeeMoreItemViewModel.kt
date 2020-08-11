package com.base.mvvm.ui.movies.seeMore.adapter

import androidx.databinding.ObservableField
import com.base.mvvm.databinding.AdapterSeeMoreBinding
import com.base.mvvm.domain.models.Movies
import java.util.*

class SeeMoreItemViewModel(itemData: Movies?, var actionDetail: (Movies) -> Unit, var binding: AdapterSeeMoreBinding) : Observable() {

    var data: Movies? = itemData

    var imageUrl = ObservableField<String>()

    init {
        imageUrl.set("https://image.tmdb.org/t/p/w500" + data!!.posterPath)
    }

    fun toDetail() {
        actionDetail(data!!)
    }
}