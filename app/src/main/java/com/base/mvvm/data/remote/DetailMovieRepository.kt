package com.base.mvvm.data.remote

import com.base.mvvm.BuildConfig
import com.base.mvvm.domain.entities.BaseResponseEntity
import com.base.mvvm.domain.entities.DetailMovieEntitiy
import com.base.mvvm.domain.entities.MovieEntity
import com.base.mvvm.domain.entities.response.BaseResponse
import com.base.mvvm.domain.entities.response.BaseResponsePagination
import com.base.mvvm.domain.models.MovieReview
import com.base.mvvm.domain.models.MovieVideos
import io.reactivex.Single

class DetailMovieRepository : BaseRepository<BaseResponseEntity<DetailMovieEntitiy>>() {

    override fun get(): Single<BaseResponseEntity<MovieEntity>>? {
        return null
    }

    override fun getById(id: Int): Single<List<BaseResponseEntity<DetailMovieEntitiy>>?>? {
        TODO("Not yet implemented")
    }

    fun getDetailMovie(id: Int): Single<DetailMovieEntitiy> {
        return remoteAPI.getDetailMovie(id, BuildConfig.API_KEY, BuildConfig.LANGUAGE)
    }

    fun getMovieReview(movieId: Int, page: Int): Single<BaseResponsePagination<MovieReview>> {
        return remoteAPI.getReviews(movieId, BuildConfig.API_KEY, page, BuildConfig.LANGUAGE)
    }

    fun getVideos(movieId: Int): Single<BaseResponse<MovieVideos>> {
        return remoteAPI.getVideo(movieId, BuildConfig.API_KEY, BuildConfig.LANGUAGE)
    }


}