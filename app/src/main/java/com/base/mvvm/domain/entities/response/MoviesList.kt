package com.base.mvvm.domain.entities.response

import com.base.mvvm.domain.models.Movies

class MoviesList {
    var page: Int? = 1
    var movies: List<Movies?>? = null
}