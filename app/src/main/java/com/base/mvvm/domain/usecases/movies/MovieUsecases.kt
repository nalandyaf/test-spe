package com.base.mvvm.domain.usecases.movies

import com.base.mvvm.data.remote.MovieRepository
import com.base.mvvm.domain.entities.BasePaginationEntity
import com.base.mvvm.domain.entities.Genre
import com.base.mvvm.domain.entities.MovieEntity
import com.base.mvvm.domain.entities.response.*
import com.base.mvvm.domain.mappers.MovieMapper
import com.base.mvvm.domain.models.MovieReview
import com.base.mvvm.domain.models.MovieVideos
import io.reactivex.Single

class MovieUsecases(mapper: MovieMapper?, repository: MovieRepository)
    : IMoviesUsecases(mapper!!, repository) {
    override fun getDiscoverMovies(page: Int,genreId:Int): Single<MoviesList> {
        return repository.getMovie(page,genreId).flatMap { responses: BasePaginationEntity<MovieEntity> ->
            val moviesList = MoviesList()
            val movies = mapper.convertToObjectList(responses.results!!)
            moviesList.movies = movies
            moviesList.page = responses.totalPages
            return@flatMap Single.just(moviesList)
        }

    }

    override fun getPopularMovies(page: Int): Single<MoviesList> {
        return repository.getPopularMovie(page).flatMap { responses: BasePaginationEntity<MovieEntity> ->
            val moviesList = MoviesList()
            val movies = mapper.convertToObjectList(responses.results!!)
            moviesList.movies = movies
            moviesList.page = responses.totalPages
            return@flatMap Single.just(moviesList)
        }
    }

    override fun getTopRatedMovies(page: Int): Single<MoviesList> {
        return repository.getTopRatedMovie(page).flatMap { responses: BasePaginationEntity<MovieEntity> ->
            val moviesList = MoviesList()
            val movies = mapper.convertToObjectList(responses.results!!)
            moviesList.movies = movies
            moviesList.page = responses.totalPages
            return@flatMap Single.just(moviesList)
        }
    }

    override fun getUpcomingMovies(page: Int): Single<MoviesList> {
        return repository.getUpcomingMovie(page).flatMap { responses: BasePaginationEntity<MovieEntity> ->
            val moviesList = MoviesList()
            val movies = mapper.convertToObjectList(responses.results!!)
            moviesList.movies = movies
            moviesList.page = responses.totalPages
            return@flatMap Single.just(moviesList)
        }
    }

    override fun getGenres(): Single<List<Genre>> {
        return repository.getGenres().flatMap { response: ResponseGenres ->
            val genreList = response.genres
            return@flatMap Single.just(genreList)
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