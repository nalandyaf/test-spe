package com.base.mvvm.ui.genre


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.ViewModelProvider
import com.base.mvvm.BR
import com.base.mvvm.R
import com.base.mvvm.ViewModelProviderFactory
import com.base.mvvm.databinding.FragmentGenreBinding
import com.base.mvvm.domain.entities.Genre
import com.base.mvvm.ui.EndlessRecyclerOnScrollListener
import com.base.mvvm.ui.base.BaseFragment
import com.base.mvvm.ui.movies.detailMovie.DetailMovieActivity
import javax.inject.Inject

class GenreFragment : BaseFragment<FragmentGenreBinding, GenreViewModel>(), GenreNavigator {

    @JvmField
    @Inject
    var factory: ViewModelProviderFactory? = null
    var binding: FragmentGenreBinding? = null


    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_genre
    override val viewModel: GenreViewModel
        get() = ViewModelProvider(this, factory!!).get(GenreViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setNavigator(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setNavigator(this)
        binding = viewDataBinding
        createLoading()
        viewModel.fetchDataGenre()
        binding!!.list.addOnScrollListener(object : EndlessRecyclerOnScrollListener() {
            override fun onLoadMore() {
                viewModel.onLoadMore()
            }
        })
    }

    override fun setDataSpinner(items: ArrayList<String>, genre: List<Genre>) {
        binding!!.spinner1.item.addAll(items)
        binding!!.spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, position: Int, id: Long) {
                viewModel.getDataMovieByGenre(genre[position].id)
                createLoading()
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {

            }
        }
    }

    override fun hideLoading() {
        dismissLoading()
    }

    override fun toDetail(id: Int?) {
        val intent = Intent(context, DetailMovieActivity::class.java)
        intent.putExtra("id", id)
        context?.startActivity(intent)
    }

    override fun clearRecycler() {
        binding!!.list.recycledViewPool.clear()
    }


    override fun handleError(throwable: Throwable?) {

    }
}