package com.base.mvvm.data.remote

import com.base.mvvm.domain.entities.response.RegistrationResponse
import com.base.mvvm.domain.mappers.UserMapper
import com.base.mvvm.domain.models.User
import com.base.mvvm.domain.usecases.user.IUserUsecases
import io.reactivex.Single

class UserRepository() : IUserUsecases()  {
    override val user: Single<User?>?
        get() = TODO("Not yet implemented")

    override fun login(username: String?, password: String?): Single<Boolean?> {
        TODO("Not yet implemented")
    }

    override fun registration(email: String?, username: String?, password: String?): Single<RegistrationResponse?>? {
        TODO("Not yet implemented")
    }

    companion object {
        @JvmStatic
        var instance: UserRepository? = null
            get() {
                if (field == null) {
                    field = UserRepository()
                }
                return field
            }
            private set
    }

}