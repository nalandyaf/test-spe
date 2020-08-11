package com.base.mvvm.domain.entities.requests

import com.google.gson.annotations.SerializedName

data class LoginRequest(
        @SerializedName("phoneno")
        private val identifier: String? = null,
        @SerializedName("password")
        private val password: String? = null
)