package com.gitug01.filmpgraphy.data.impl

import com.gitug01.filmpgraphy.R
import com.gitug01.filmpgraphy.domain.entity.FilmEntity
import com.gitug01.filmpgraphy.domain.repo.FilmRepo

class FilmRepoImpl() : FilmRepo{


    var counter: Int = 0

    private var cache: ArrayList<FilmEntity> = ArrayList()
    override fun films(): ArrayList<FilmEntity> {
        return cache
    }


    override fun addFilm(film: FilmEntity): Int {
        var newId = ++counter
        film.id = newId
        cache.add(film)
        return newId
    }

    override fun removeFilm(id: Int) {
        cache.removeAt(id)
    }

    override fun editFilm(id: Int, film: FilmEntity) {
        TODO("Not yet implemented")
    }


}