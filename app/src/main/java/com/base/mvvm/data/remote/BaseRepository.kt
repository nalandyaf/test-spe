package com.base.mvvm.data.remote

import com.base.mvvm.domain.entities.BaseResponseEntity
import com.base.mvvm.domain.entities.MovieEntity
import io.reactivex.Single
import javax.inject.Singleton

@Singleton
abstract class BaseRepository<E : BaseResponseEntity<*>?> {
    protected var entities: List<E> = arrayListOf()
    protected var remoteAPI: RemoteAPI = RetrofitFactory.instance!!.remoteAPI
    abstract fun get(): Single<BaseResponseEntity<MovieEntity>>?
    abstract fun getById(id: Int): Single<List<E>?>?

    companion object {
        private val instance: BaseRepository<*>? = null
    }

}