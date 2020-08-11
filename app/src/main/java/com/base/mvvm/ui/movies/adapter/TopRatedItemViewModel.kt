package com.base.mvvm.ui.movies.adapter

import androidx.databinding.ObservableField
import com.base.mvvm.databinding.AdapterMovieTopRatedBinding
import com.base.mvvm.domain.models.Movies
import com.squareup.picasso.Picasso
import java.util.*

class TopRatedItemViewModel(itemData: Movies?,
                            var actionDetail: (Movies) -> Unit,
                            var binding: AdapterMovieTopRatedBinding) : Observable() {

    var year = ObservableField<String>()
    var title = ObservableField<String>()
    var votes = ObservableField<String>()
    var rating = ObservableField<Float>()
    var voteAverage = ObservableField<String>()

    var data: Movies? = itemData
    var imageUrl = ObservableField<String>()

    init {
        year.set(data?.releaseDate!!.substring(0, 4))
        title.set(data?.title)
        votes.set(data?.voteCount.toString() + " VOTES")
        val ratingData = ((data?.voteAverage?.times(5))?.div(10))?.toFloat()
        rating.set(ratingData)
        voteAverage.set(data?.voteAverage.toString())
        imageUrl.set("https://image.tmdb.org/t/p/w500" + data?.backdropPath)

    }

    fun toDetail(){
        with(binding){
            executePendingBindings()
        }
        actionDetail(data!!)
    }
}