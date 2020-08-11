package com.base.mvvm.domain.entities

import lombok.Getter
import lombok.Setter

@Setter
@Getter
open class BaseResponseEntity<E : BaseObjectEntity?>{
        val results: List<E>? = null
}
