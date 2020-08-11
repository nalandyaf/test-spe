package com.base.mvvm.domain.entities.response

import com.base.mvvm.domain.models.MovieReview

class ResponseReview {
    var totalPages: Int? = 1
    var movieReview: List<MovieReview>? = null
}