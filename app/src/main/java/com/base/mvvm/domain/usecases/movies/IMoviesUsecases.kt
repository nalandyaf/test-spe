package com.base.mvvm.domain.usecases.movies

import com.base.mvvm.data.remote.MovieRepository
import com.base.mvvm.domain.entities.Genre
import com.base.mvvm.domain.entities.response.MoviesList
import com.base.mvvm.domain.entities.response.ResponseReview
import com.base.mvvm.domain.entities.response.ResponseVideo
import com.base.mvvm.domain.exceptions.MapperException
import com.base.mvvm.domain.mappers.MovieMapper
import com.base.mvvm.domain.usecases.base.BaseUsecase
import io.reactivex.Single

abstract class IMoviesUsecases(mapper: MovieMapper, movieRepository: MovieRepository?)
    : BaseUsecase<MovieMapper, MovieRepository>(mapper, movieRepository!!) {
    @Throws(MapperException::class)
    abstract fun getDiscoverMovies(page: Int, genreId: Int): Single<MoviesList>

    @Throws(MapperException::class)
    abstract suspend fun getPopularMovies(page: Int): Single<MoviesList>

    @Throws(MapperException::class)
    abstract suspend fun getTopRatedMovies(page: Int): Single<MoviesList>

    @Throws(MapperException::class)
    abstract suspend fun getUpcomingMovies(page: Int): Single<MoviesList>

    @Throws(MapperException::class)
    abstract fun getGenres(): Single<List<Genre>>

    @Throws(MapperException::class)
    abstract fun getMovieReview(movieId: Int, page: Int): Single<ResponseReview>

    @Throws(MapperException::class)
    abstract fun getMovieVideos(movieId: Int): Single<ResponseVideo>


}