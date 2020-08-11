package com.base.mvvm.domain.entities

import com.google.gson.annotations.SerializedName

open class MovieEntity : BaseObjectEntity() {

    var ids: Long = 0
    @SerializedName("vote_count")
    var voteCount: Int = 0
    var video: Boolean = false
    @SerializedName("vote_average")
    var voteAverage: Double = 0.0
    var title: String? = null
    var popularity: Double = 0.0
    @SerializedName("poster_path")
    var posterPath: String? = null
    @SerializedName("original_language")
    var originalLanguage: String? = null
    @SerializedName("original_title")
    var originalTitle: String? = null
    @SerializedName("genre_ids")
    var genreIds: MutableList<Long>? = null
    @SerializedName("backdrop_path")
    var backdropPath: String? = null
    var adult: Boolean = false
    var overview: String? = null
    @SerializedName("release_date")
    var releaseDate: String? = null
    var genres: List<Genre>? = null

}

open class Genre(
        val id: Int,
        val name: String
)
