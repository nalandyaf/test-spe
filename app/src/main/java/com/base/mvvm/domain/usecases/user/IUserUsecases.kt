package com.base.mvvm.domain.usecases.user

import com.base.mvvm.data.remote.UserRepository
import com.base.mvvm.domain.entities.response.RegistrationResponse
import com.base.mvvm.domain.exceptions.MapperException
import com.base.mvvm.domain.mappers.UserMapper
import com.base.mvvm.domain.models.User
import io.reactivex.Single

abstract class IUserUsecases() {
    @get:Throws(MapperException::class)
    abstract val user: Single<User?>?

    @Throws(MapperException::class)
    abstract fun login(username: String?, password: String?): Single<Boolean?>

    @Throws(MapperException::class)
    abstract fun registration(email: String?, username: String?, password: String?): Single<RegistrationResponse?>?
}