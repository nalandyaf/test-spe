package com.base.mvvm.ui.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.base.mvvm.databinding.AdapterMoviePopularBinding
import com.base.mvvm.domain.models.Movies
import com.base.mvvm.ui.base.BaseAdapter

class AdapterPopular(data: ArrayList<Movies>, action: (Movies) -> Unit) : BaseAdapter<Movies>(data) {
    private lateinit var binding: AdapterMoviePopularBinding

    private val action: (Movies) -> Unit = action

    override fun createHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = AdapterMoviePopularBinding.inflate(LayoutInflater.from(parent.context)
                , parent, false)
        return GenericViewHolder(binding)
    }

    override fun bindingViewHolder(holder: GenericViewHolder, position: Int) {
        (holder.viewDataBinding as AdapterMoviePopularBinding).viewModel =
                PopularItemViewModel(getItem(position),action, binding)
    }
}