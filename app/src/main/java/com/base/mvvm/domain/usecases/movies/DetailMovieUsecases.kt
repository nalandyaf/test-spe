package com.base.mvvm.domain.usecases.movies

import com.base.mvvm.data.remote.DetailMovieRepository
import com.base.mvvm.domain.entities.DetailMovieEntitiy
import com.base.mvvm.domain.entities.response.BaseResponse
import com.base.mvvm.domain.entities.response.BaseResponsePagination
import com.base.mvvm.domain.entities.response.ResponseReview
import com.base.mvvm.domain.entities.response.ResponseVideo
import com.base.mvvm.domain.mappers.DetailMovieMapper
import com.base.mvvm.domain.models.DetailMovies
import com.base.mvvm.domain.models.MovieReview
import com.base.mvvm.domain.models.MovieVideos
import io.reactivex.Single

class DetailMovieUsecases(mapper: DetailMovieMapper?, repository: DetailMovieRepository?)
    : IDetailMovieUsecases(mapper!!, repository) {
    override fun getDetailMovies(movieId: Int): Single<DetailMovies> {
        return repository.getDetailMovie(movieId).flatMap { responses: DetailMovieEntitiy ->
            val detailMovies = mapper.convertToObject(responses)
            return@flatMap Single.just(detailMovies)
        }
    }

    override fun getMovieReview(movieId: Int, page: Int): Single<ResponseReview> {
        return repository.getMovieReview(movieId, page).flatMap { responses: BaseResponsePagination<MovieReview> ->
            val responseReview = ResponseReview()
            responseReview.movieReview = responses.results
            responseReview.totalPages = responses.totalPages
            return@flatMap Single.just(responseReview)
        }
    }

    override fun getMovieVideos(movieId: Int): Single<ResponseVideo> {
        return repository.getVideos(movieId).flatMap { response: BaseResponse<MovieVideos> ->
            val responseVideo = ResponseVideo()
            responseVideo.movieVideos = response.results
            return@flatMap Single.just(responseVideo)
        }
    }
}