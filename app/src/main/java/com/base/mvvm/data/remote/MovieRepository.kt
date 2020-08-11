package com.base.mvvm.data.remote

import com.base.mvvm.BuildConfig
import com.base.mvvm.domain.entities.BasePaginationEntity
import com.base.mvvm.domain.entities.BaseResponseEntity
import com.base.mvvm.domain.entities.MovieEntity
import com.base.mvvm.domain.entities.response.BaseResponse
import com.base.mvvm.domain.entities.response.BaseResponsePagination
import com.base.mvvm.domain.entities.response.ResponseGenres
import com.base.mvvm.domain.models.MovieReview
import com.base.mvvm.domain.models.MovieVideos
import io.reactivex.Single

class MovieRepository : BaseRepository<BaseResponseEntity<MovieEntity>>() {
    override fun get(): Single<BaseResponseEntity<MovieEntity>>? {
        return null
    }

    override fun getById(id: Int): Single<List<BaseResponseEntity<MovieEntity>>?>? {
        TODO("Not yet implemented")
    }

    fun getMovie(page: Int, genreId: Int): Single<BasePaginationEntity<MovieEntity>> {
        return remoteAPI.getMovies(BuildConfig.API_KEY, BuildConfig.LANGUAGE, "popularity.desc", genreId.toString(), page)
    }

    fun getPopularMovie(page: Int): Single<BasePaginationEntity<MovieEntity>> {
        return remoteAPI.getPopularMovies(BuildConfig.API_KEY, BuildConfig.LANGUAGE, page)
    }

    fun getTopRatedMovie(page: Int): Single<BasePaginationEntity<MovieEntity>> {
        return remoteAPI.getTopRatedMovies(BuildConfig.API_KEY, BuildConfig.LANGUAGE, page)
    }

    fun getUpcomingMovie(page: Int): Single<BasePaginationEntity<MovieEntity>> {
        return remoteAPI.getUpcoming(BuildConfig.API_KEY, BuildConfig.LANGUAGE, page)
    }

    fun getMovieReview(movieId: Int, page: Int): Single<BaseResponsePagination<MovieReview>> {
        return remoteAPI.getReviews(movieId, BuildConfig.API_KEY, page, BuildConfig.LANGUAGE)
    }

    fun getGenres(): Single<ResponseGenres> {
        return remoteAPI.getGenre(BuildConfig.API_KEY, BuildConfig.LANGUAGE)
    }

    fun getVideos(movieId: Int): Single<BaseResponse<MovieVideos>> {
        return remoteAPI.getVideo(movieId, BuildConfig.API_KEY, BuildConfig.LANGUAGE)
    }


}