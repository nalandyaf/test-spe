package com.base.mvvm.ui.movies.detailMovie

import androidx.databinding.ObservableField
import com.base.mvvm.domain.entities.response.ResponseReview
import com.base.mvvm.domain.entities.response.ResponseVideo
import com.base.mvvm.domain.exceptions.MapperException
import com.base.mvvm.domain.models.DetailMovies
import com.base.mvvm.domain.usecases.movies.IDetailMovieUsecases
import com.base.mvvm.ui.base.BaseViewModel
import com.base.mvvm.ui.movies.detailMovie.adapter.AdapterReview
import com.base.mvvm.ui.movies.detailMovie.adapter.AdapterTrailer
import com.base.mvvm.utils.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailMovieViewModel(baseUsecases: IDetailMovieUsecases, schedulerProvider: SchedulerProvider)
    : BaseViewModel<IDetailMovieUsecases, DetailMovieNavigator>(baseUsecases, schedulerProvider) {

    var status = ObservableField<String>()
    var title = ObservableField<String>()
    var genre = ObservableField<String>()
    var playTime = ObservableField<String>()
    var releaseDate = ObservableField<String>()
    var overView = ObservableField<String>()

    private var page: Int? = 1
    private var totalPages: Int? = 0


    private lateinit var adapterTrailer: AdapterTrailer

    private lateinit var adapterReview: AdapterReview

    override fun defineLayout() {

    }

    fun fetchData(idMovie: Int) {
        getDataDetail(idMovie)
        getDataVideos(idMovie)
        getDataReview(idMovie)
    }

    fun getDataReview(idMovie: Int) {
        try {
            compositeDisposable.add(baseUsecase!!.getMovieReview(idMovie!!, page!!)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onSuccessGetDataReviews, this::onError))
        } catch (e: MapperException) {
            e.printStackTrace()
            onError(e)
        }
    }

    fun getDataVideos(idMovie: Int) {
        try {
            compositeDisposable.add(baseUsecase!!.getMovieVideos(idMovie!!)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onSucessGetDataVideo, this::onError))
        } catch (e: MapperException) {
            e.printStackTrace()
            onError(e)
        }
    }

    fun getDataDetail(idMovie: Int) {
        try {
            compositeDisposable.add(baseUsecase!!.getDetailMovies(idMovie!!)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onSuccessGetData, this::onError))
        } catch (e: MapperException) {
            e.printStackTrace()
            onError(e)
        }
    }

    fun onSuccessGetData(detailMovies: DetailMovies) {
        status.set(detailMovies.status)
        title.set(detailMovies.title)
        var genres = ""
        detailMovies.genres?.forEach { genres += " | " + it.name }
        genre.set(genres)
        playTime.set(detailMovies.runTime.toString() + "Minutes")
        releaseDate.set(detailMovies.releaseDate?.substring(0, 4))
        overView.set(detailMovies.overview)
        navigator?.hideLoading()
    }

    fun onSucessGetDataVideo(responseVide: ResponseVideo) {
        navigator?.setupPlayer(responseVide.movieVideos?.get(0)?.key)
        adapterTrailer.addItems(responseVide.movieVideos!!)
    }

    fun onSuccessGetDataReviews(responseReview: ResponseReview) {
        adapterReview.addItems(responseReview.movieReview!!)
        totalPages = responseReview.totalPages
    }

    fun getAdapterTrailer(): AdapterTrailer {
        adapterTrailer = AdapterTrailer(ArrayList(), ::selectedTrailer)
        return adapterTrailer
    }

    fun getAdapterReview(): AdapterReview {
        adapterReview = AdapterReview(ArrayList())
        return adapterReview
    }

    fun selectedTrailer(key: String) {
        navigator?.setupPlayer(key)
    }

    fun onLoadMore(idMovie: Int) {
        if (page!! < totalPages!!.minus(1)) {
            page = page!!.plus(1)
            getDataReview(idMovie)
        }
    }
}