package com.base.mvvm.domain.entities.response

import com.google.gson.annotations.SerializedName

data class RegistrationResponse(
        @SerializedName("jwt")
        val jwt: String? = null
)