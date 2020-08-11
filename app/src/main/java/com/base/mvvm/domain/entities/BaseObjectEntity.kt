package com.base.mvvm.domain.entities

import com.google.gson.annotations.SerializedName


open class BaseObjectEntity {
    @SerializedName("id")
    var id: Int? = 0
}