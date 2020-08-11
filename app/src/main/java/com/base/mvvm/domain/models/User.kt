package com.base.mvvm.domain.models

import com.google.gson.annotations.SerializedName

data class User(
        @SerializedName("username")
        private val username: String? = null,
        @SerializedName("email")
        private val email: String? = null,
        @SerializedName("provider")
        private val provider: String? = null,
        @SerializedName("password")
        private val password: String? = null,
        @SerializedName("resetPasswordToken")
        private val resetPasswordToken: String? = null,
        @SerializedName("confirmed")
        private val confirmed: Boolean = false,
        @SerializedName("blocked")
        private val blocked: Boolean = false,
        @SerializedName("role")
        private val role: String? = null,
        @SerializedName("firstName")
        private val firstName: String? = null,
        @SerializedName("lastName")
        private val lastName: String? = null,
        @SerializedName("language")
        private val language: String? = null,
        @SerializedName("activecart")
        private val activecart: String? = null,
        @SerializedName("phone")
        private val phone: String? = null,
        @SerializedName("firebaseToken")
        private val firebaseToken: String? = null,
        @SerializedName("referralCode")
        private val referralCode: String? = null,
        @SerializedName("tourAgencies")
        private val tourAgencies: List<String>? = null,
        @SerializedName("carts")
        private val carts: List<String>? = null
) : BaseObject()