package com.base.mvvm.domain.entities

import com.google.gson.annotations.SerializedName

class BasePaginationEntity<E : BaseObjectEntity> : BaseResponseEntity<E>() {
    val page: Int? = 0

    @SerializedName("total_pages")
    val totalPages: Int? = 0

    @SerializedName("total_results")
    val totalResult: Int? = 0

}


