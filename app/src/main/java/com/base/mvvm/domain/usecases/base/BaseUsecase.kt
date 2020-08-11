package com.base.mvvm.domain.usecases.base

import com.base.mvvm.data.remote.BaseRepository
import com.base.mvvm.domain.mappers.BaseMapper

abstract class BaseUsecase<M : BaseMapper<*, *>?, R : BaseRepository<*>?>(protected var mapper: M, protected var repository: R)