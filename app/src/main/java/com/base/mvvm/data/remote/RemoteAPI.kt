package com.base.mvvm.data.remote

import com.base.mvvm.domain.entities.BasePaginationEntity
import com.base.mvvm.domain.entities.DetailMovieEntitiy
import com.base.mvvm.domain.entities.MovieEntity
import com.base.mvvm.domain.entities.requests.LoginRequest
import com.base.mvvm.domain.entities.requests.RegistrationRequest
import com.base.mvvm.domain.entities.response.*
import com.base.mvvm.domain.models.MovieReview
import com.base.mvvm.domain.models.MovieVideos
import io.reactivex.Single
import retrofit2.http.*

/**
 * Retrofit API
 */
interface RemoteAPI {

    @POST("/api/login")
    fun login(@Body loginRequest: LoginRequest?): Single<LoginResponse?>?

    @POST("/auth/local/register")
    fun registration(@Body registrationRequest: RegistrationRequest?): Single<RegistrationResponse?>?

    @GET("movie/{movie_id}")
    fun getDetailMovie(@Path("movie_id") movieId: Int,
                       @Query("api_key") apiKey: String,
                       @Query("language") language: String): Single<DetailMovieEntitiy>

    @GET("movie/{movie_id}/videos")
    fun getVideo(@Path("movie_id") movieId: Int,
                 @Query("api_key") apiKey: String,
                 @Query("language") language: String): Single<BaseResponse<MovieVideos>>

    @GET("movie/{movie_id}/reviews")
    fun getReviews(@Path("movie_id") movieId: Int,
                   @Query("api_key") apiKey: String,
                   @Query("page") page: Int,
                   @Query("language") language: String): Single<BaseResponsePagination<MovieReview>>

    @GET("discover/movie")
    fun getMovies(@Query("api_key") apiKey: String,
                  @Query("language") language: String,
                  @Query("sort_by") sortBy: String,
                  @Query("with_genres") genreId: String,
                  @Query("page") page: Int): Single<BasePaginationEntity<MovieEntity>>

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String,
                         @Query("language") language: String,
                         @Query("page") page: Int): Single<BasePaginationEntity<MovieEntity>>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String,
                          @Query("language") language: String,
                          @Query("page") page: Int): Single<BasePaginationEntity<MovieEntity>>

    @GET("movie/upcoming")
    fun getUpcoming(@Query("api_key") apiKey: String,
                    @Query("language") language: String,
                    @Query("page") page: Int): Single<BasePaginationEntity<MovieEntity>>


    @GET("genre/movie/list")
    fun getGenre(@Query("api_key") apiKey: String,
                 @Query("language") language: String): Single<ResponseGenres>

}