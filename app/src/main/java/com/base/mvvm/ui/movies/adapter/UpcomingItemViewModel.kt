package com.base.mvvm.ui.movies.adapter

import androidx.databinding.ObservableField
import com.base.mvvm.databinding.AdapterMovieUpcomingBinding
import com.base.mvvm.domain.models.Movies
import com.squareup.picasso.Picasso
import java.util.*

class UpcomingItemViewModel(itemData: Movies?, var actionDetail: (Movies) -> Unit, var binding: AdapterMovieUpcomingBinding) : Observable() {
    var releaseDate = ObservableField<String>()

    var data: Movies? = itemData
    var imageUrl = ObservableField<String>()

    init {
        releaseDate.set(data?.releaseDate)
        imageUrl.set("https://image.tmdb.org/t/p/w500" + data?.posterPath)
    }

    fun toDetail() {
        with(binding) {
            executePendingBindings()
        }
        actionDetail(data!!)
    }
}