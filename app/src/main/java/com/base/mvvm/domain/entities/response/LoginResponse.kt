package com.base.mvvm.domain.entities.response

import com.base.mvvm.domain.entities.BaseObjectEntity
import com.google.gson.annotations.SerializedName

data class LoginResponse(
        @SerializedName("jwt")
        val jwt: String? = null
): BaseObjectEntity()