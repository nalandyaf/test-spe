package com.base.mvvm.domain.entities.response

open class BaseResponse<E> {
    val id: Int? = 0
    val results: List<E>? = null
}