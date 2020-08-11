package com.base.mvvm.ui.movies.seeMore

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
class SeeMoreViewModel(movieUsecases: IMoviesUsecases, schedulerProvider: SchedulerProvider)
    : BaseViewModel<IMoviesUsecases?, SeeMoreNavigator>(movieUsecases, schedulerProvider) {

    var page: Int? = 1
    var totalPages: Int? = 0

    private lateinit var adapterSeeMoreAdapter: SeeMoreAdapter

    override fun defineLayout() {

    }

    fun onLoadMore(type: Int) {
        if (page!! < totalPages!!.minus(1)) {
            page = page!!.plus(1)
            fetchData(type)
        }
    }

    fun fetchData(type: Int) {
        when (type) {
            SeeMoreActivity.TYPE_POPULAR -> getDataPopular(page)
            SeeMoreActivity.TYPE_TOP_RATED -> getDataTopRated(page)
            SeeMoreActivity.TYPE_UPCOMING -> getDataUpcoming(page)
        }
    }

    private fun getDataUpcoming(page: Int?) {
        try {
            compositeDisposable.add(baseUsecase!!.getUpcomingMovies(page!!)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onSuccessGetData, this::onError))
        } catch (e: MapperException) {
            e.printStackTrace()
            onError(e)
        }
    }

    private fun getDataTopRated(page: Int?) {
        try {
            compositeDisposable.add(baseUsecase!!.getTopRatedMovies(page!!)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onSuccessGetData, this::onError))
        } catch (e: MapperException) {
            e.printStackTrace()
            onError(e)
        }
    }

    private fun getDataPopular(page: Int?) {
        try {
            compositeDisposable.add(baseUsecase!!.getPopularMovies(page!!)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onSuccessGetData, this::onError))
        } catch (e: MapperException) {
            e.printStackTrace()
            onError(e)
        }
    }

    fun onSuccessGetData(moviesList: MoviesList) {
        navigator?.hideLoad()
        totalPages = moviesList.page
        adapterSeeMoreAdapter.addItems(moviesList.movies as List<Movies>)
    }

    fun getAdapter(): SeeMoreAdapter {
        adapterSeeMoreAdapter = SeeMoreAdapter(ArrayList(), ::toDetailMovie)
        return adapterSeeMoreAdapter
    }

    fun toDetailMovie(movies: Movies) {
        navigator?.toDetail(movies.id)
    }

}