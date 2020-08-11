package com.base.mvvm.ui.movies.detailMovie.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.base.mvvm.databinding.AdapterTrailerBinding
import com.base.mvvm.domain.models.MovieVideos
import com.base.mvvm.ui.base.BaseAdapter

class AdapterTrailer(data: ArrayList<MovieVideos>, action: (String) -> Unit)
    : BaseAdapter<MovieVideos>(data) {

    private lateinit var binding: AdapterTrailerBinding
    private val action: (String) -> Unit = action

    override fun createHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = AdapterTrailerBinding
                .inflate(LayoutInflater.from(parent.context)
                        , parent, false)
        return GenericViewHolder(binding)
    }

    override fun bindingViewHolder(holder: GenericViewHolder, position: Int) {
        (holder.viewDataBinding as AdapterTrailerBinding).viewModel =
                ItemTrailerViewModel(getItem(position), action, binding)
    }
}