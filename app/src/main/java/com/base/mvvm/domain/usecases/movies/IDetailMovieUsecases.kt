package com.base.mvvm.domain.usecases.movies

import com.base.mvvm.data.remote.DetailMovieRepository
import com.base.mvvm.domain.entities.response.ResponseReview
import com.base.mvvm.domain.entities.response.ResponseVideo
import com.base.mvvm.domain.exceptions.MapperException
import com.base.mvvm.domain.mappers.DetailMovieMapper
import com.base.mvvm.domain.models.DetailMovies
import com.base.mvvm.domain.usecases.base.BaseUsecase
import io.reactivex.Single

abstract class IDetailMovieUsecases(mapper: DetailMovieMapper, detailMovieRepository: DetailMovieRepository?)
    : BaseUsecase<DetailMovieMapper, DetailMovieRepository>(mapper, detailMovieRepository!!) {

    @Throws(MapperException::class)
    abstract fun getDetailMovies(movieId:Int) : Single<DetailMovies>

    @Throws(MapperException::class)
    abstract fun getMovieReview(movieId: Int, page: Int): Single<ResponseReview>

    @Throws(MapperException::class)
    abstract fun getMovieVideos(movieId: Int): Single<ResponseVideo>
}