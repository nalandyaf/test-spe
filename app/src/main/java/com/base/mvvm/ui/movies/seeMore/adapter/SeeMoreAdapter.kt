package com.base.mvvm.ui.movies.seeMore.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.base.mvvm.databinding.AdapterSeeMoreBinding
import com.base.mvvm.domain.models.Movies
import com.base.mvvm.ui.base.BaseAdapter

class SeeMoreAdapter(data: ArrayList<Movies>, action: (Movies) -> Unit) : BaseAdapter<Movies>(data) {

    private lateinit var binding: AdapterSeeMoreBinding
    private var action: (Movies) -> Unit = action

    override fun createHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = AdapterSeeMoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenericViewHolder(binding)
    }

    override fun bindingViewHolder(holder: GenericViewHolder, position: Int) {
        (holder.viewDataBinding as AdapterSeeMoreBinding).viewModel =
                SeeMoreItemViewModel(getItem(position),action, binding)
    }
}