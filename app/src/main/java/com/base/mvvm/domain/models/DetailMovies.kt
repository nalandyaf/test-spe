package com.base.mvvm.domain.models

import com.base.mvvm.domain.entities.Genre

class DetailMovies (
    var status: String? = null,
    var title: String? = null,
    var runTime: Int? = null,
    var releaseDate: String? = null,
    var overview: String? = null,
    var genres: List<Genre>? = null
) : BaseObject()