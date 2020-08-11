package com.base.mvvm.ui.movies.detailMovie.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.base.mvvm.databinding.AdapterReviewsBinding
import com.base.mvvm.domain.models.MovieReview
import com.base.mvvm.ui.base.BaseAdapter

class AdapterReview(data: ArrayList<MovieReview>) : BaseAdapter<MovieReview>(data) {

    private lateinit var binding: AdapterReviewsBinding

    override fun createHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = AdapterReviewsBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        return GenericViewHolder(binding)
    }

    override fun bindingViewHolder(holder: GenericViewHolder, position: Int) {
        (holder.viewDataBinding as AdapterReviewsBinding).vieModel =
                ReviewItemViewModel(getItem(position)!!, binding)
    }
}