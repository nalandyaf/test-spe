package com.base.mvvm.ui.movies.detailMovie.adapter

import androidx.databinding.ObservableField
import com.base.mvvm.databinding.AdapterReviewsBinding
import com.base.mvvm.domain.models.MovieReview
import java.util.*

class ReviewItemViewModel(itemData: MovieReview, var binding: AdapterReviewsBinding) : Observable() {

    var data: MovieReview? = itemData
    var author = ObservableField<String>()
    var content = ObservableField<String>()

    init {
        author.set(data?.author)
        content.set(data?.content)
    }
}