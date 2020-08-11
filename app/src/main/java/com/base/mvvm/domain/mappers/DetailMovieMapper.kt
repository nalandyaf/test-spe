package com.base.mvvm.domain.mappers

import com.base.mvvm.domain.entities.DetailMovieEntitiy
import com.base.mvvm.domain.exceptions.MapperException
import com.base.mvvm.domain.models.DetailMovies

class DetailMovieMapper : BaseMapper<DetailMovieEntitiy?, DetailMovies?>() {
    override fun createObject(): DetailMovies? {
        return DetailMovies()
    }

    override fun createEntity(): DetailMovieEntitiy? {
        return DetailMovieEntitiy()
    }

    @Throws(MapperException::class)
    override fun defineObject(`object`: DetailMovies?): DetailMovies? {
        try {
            `object`?.id = entity?.id
            `object`?.genres = entity?.genres
            `object`?.overview = entity?.overview
            `object`?.releaseDate = entity?.release_date
            `object`?.runTime = entity?.runtime
            `object`?.title = entity?.original_title
            `object`?.status = entity?.status
            return `object`
        } catch (e: Exception) {
            throw MapperException(e.message)
        }
    }

    @Throws(MapperException::class)
    override fun defineEntity(entity: DetailMovieEntitiy?): DetailMovieEntitiy? {
        try {
            return entity
        } catch (e: Exception) {
            throw MapperException(e.message)
        }
    }


}