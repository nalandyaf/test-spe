package com.base.mvvm.ui.genre

import com.base.mvvm.domain.entities.Genre
import com.base.mvvm.domain.entities.response.MoviesList
import com.base.mvvm.domain.exceptions.MapperException
import com.base.mvvm.domain.models.Movies
import com.base.mvvm.domain.usecases.movies.IMoviesUsecases
import com.base.mvvm.ui.base.BaseViewModel
import com.base.mvvm.ui.movies.seeMore.adapter.SeeMoreAdapter
import com.base.mvvm.utils.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Suppress("UNCHECKED_CAST")
class GenreViewModel(movieUsecases: IMoviesUsecases, schedulerProvider: SchedulerProvider)
    : BaseViewModel<IMoviesUsecases?, GenreNavigator>(movieUsecases, schedulerProvider) {

    var page: Int? = 1
    var totalPage: Int? = 0
    var selectedGenre: Int? = null
    private lateinit var adapterSeeMoreAdapter: SeeMoreAdapter

    override fun defineLayout() {

    }

    fun fetchDataGenre() {
        try {
            compositeDisposable.add(baseUsecase!!.getGenres()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onSuccessGetDataGenre, this::onError))
        } catch (e: MapperException) {
            e.printStackTrace()
            onError(e)
        }
    }

    fun onSuccessGetDataGenre(genre: List<Genre>) {
        val items = arrayListOf<String>()
        genre.forEach { items.add(it.name) }
        navigator?.setDataSpinner(items, genre)
        navigator?.hideLoading()
    }

    fun getDataMovieByGenre(id: Int) {
        page = 1
        selectedGenre = id
        try {
            adapterSeeMoreAdapter.clearItems()
            navigator?.clearRecycler()
            compositeDisposable.add(baseUsecase!!.getDiscoverMovies(page!!, selectedGenre!!)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onSuccessGetData, this::onError))
        } catch (e: MapperException) {
            e.printStackTrace()
            onError(e)
        }
    }

    fun onSuccessGetData(moviesList: MoviesList) {
        totalPage = moviesList.page
        adapterSeeMoreAdapter.addItems(moviesList.movies as List<Movies>)
        navigator?.hideLoading()
    }

    fun getAdapter(): SeeMoreAdapter {
        adapterSeeMoreAdapter = SeeMoreAdapter(ArrayList(), ::toDetailMovie)
        return adapterSeeMoreAdapter
    }

    fun toDetailMovie(movies: Movies) {
        navigator?.toDetail(movies.id)
    }

    fun onLoadMore() {
        if (page!! < totalPage!!.minus(1)) {
            page = page!!.plus(1)
            fetchDataMovie()
        }
    }

    private fun fetchDataMovie() {
        try {
            compositeDisposable.add(baseUsecase!!.getDiscoverMovies(page!!, selectedGenre!!)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onSuccessGetData, this::onError))
        } catch (e: MapperException) {
            e.printStackTrace()
            onError(e)
        }
    }
}