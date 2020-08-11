package com.base.mvvm.domain.usecases.user

import com.base.mvvm.data.local.PreferencesManager
import com.base.mvvm.data.remote.UserRepository
import com.base.mvvm.domain.entities.requests.LoginRequest
import com.base.mvvm.domain.entities.requests.RegistrationRequest
import com.base.mvvm.domain.entities.response.LoginResponse
import com.base.mvvm.domain.entities.response.RegistrationResponse
import com.base.mvvm.domain.mappers.UserMapper
import com.base.mvvm.domain.models.User
import io.reactivex.Single

class UserUsecases(private val mapper: UserMapper, private val repository: UserRepository?) : IUserUsecases() {
    override val user: Single<User?>?
        get() = null

    override fun login(username: String?, password: String?): Single<Boolean?> {
        TODO("Not yet implemented")
    }

    override fun registration(email: String?, username: String?, password: String?): Single<RegistrationResponse?>? {
        TODO("Not yet implemented")
    }

//    override fun login(username: String?, password: String?): Single<Boolean?> {
////        return repository!!.login(LoginRequest(username, password))!!.flatMap { response: LoginResponse? ->
////            if (response?.jwt != null) {
////                PreferencesManager.instance!!.prefToken = (response.jwt)
////                return@flatMap Single.just(true)
////            }
////            Single.just(false)
////        }
//    }
//
//    override fun registration(email: String?, username: String?, password: String?): Single<RegistrationResponse?>? {
////        return repository!!.registration(RegistrationRequest(username, email, password))
//    }

}