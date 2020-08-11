package com.base.mvvm.domain.mappers

import com.base.mvvm.domain.entities.BaseObjectEntity
import com.base.mvvm.domain.exceptions.MapperException
import com.base.mvvm.domain.models.BaseObject
import java.util.*

abstract class BaseMapper<E : BaseObjectEntity?, O : BaseObject?> {
    protected var entity: E? = null
    protected var `object`: O? = null
    @Throws(MapperException::class)
    fun convertToObject(entity: E): O {
        return try {
            this.entity = entity
            `object` = createObject()
            `object`?.id = (entity!!.id)
            defineObject(`object`)
        } catch (e: Exception) {
            throw MapperException(e.message)
        }
    }

    @Throws(MapperException::class)
    fun convertToEntity(`object`: O): E {
        return try {
            entity = createEntity()
            entity?.id = (`object`!!.id)
            defineEntity(entity)
        } catch (e: Exception) {
            throw MapperException(e.message)
        }
    }

    @Throws(MapperException::class)
    fun convertToObjectList(entityList: List<E>): List<O> {
        val list: MutableList<O> = ArrayList()
        for (entity in entityList) {
            list.add(convertToObject(entity))
        }
        return list
    }

    @Throws(MapperException::class)
    fun convertToEntityList(objectList: List<O>): List<E> {
        val list: MutableList<E> = ArrayList()
        for (`object` in objectList) {
            list.add(convertToEntity(`object`))
        }
        return list
    }

    abstract fun createObject(): O
    abstract fun createEntity(): E
    @Throws(MapperException::class)
    abstract fun defineObject(`object`: O?): O

    @Throws(MapperException::class)
    abstract fun defineEntity(entity: E?): E
}