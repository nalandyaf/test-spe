package com.base.mvvm.domain.entities.response

import com.google.gson.annotations.SerializedName

open class BaseResponsePagination<E> {

    val id: Int? = 0

    val page: Int? = 0

    @SerializedName("total_pages")
    val totalPages: Int? = 0

    @SerializedName("total_results")
    val totalResult: Int? = 0

    val results: List<E>? = null
}