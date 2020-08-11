package com.base.mvvm.domain.mappers

import com.base.mvvm.domain.entities.MovieEntity
import com.base.mvvm.domain.exceptions.MapperException
import com.base.mvvm.domain.models.Movies

class MovieMapper : BaseMapper<MovieEntity?, Movies?>() {
    override fun createObject(): Movies {
        return Movies()
    }

    override fun createEntity(): MovieEntity? {
        return MovieEntity()
    }

    @Throws(MapperException::class)
    override fun defineObject(`object`: Movies?): Movies? {
        try {
            `object`?.id = entity!!.id
            `object`?.title = entity!!.title
            `object`?.backdropPath = entity!!.backdropPath
            `object`?.releaseDate = entity!!.releaseDate
            `object`?.voteAverage = entity!!.voteAverage
            `object`?.voteCount = entity!!.voteCount
            `object`?.posterPath = entity!!.posterPath
            return `object`

        } catch (e: Exception) {
            throw MapperException(e.message)
        }
    }

    @Throws(MapperException::class)
    override fun defineEntity(entity: MovieEntity?): MovieEntity? {
        try {
            return entity
        } catch (e: Exception) {
            throw MapperException(e.message)
        }
    }

}