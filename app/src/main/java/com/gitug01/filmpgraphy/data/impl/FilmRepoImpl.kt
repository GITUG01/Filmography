package com.gitug01.filmpgraphy.data.impl

import com.gitug01.filmpgraphy.R
import com.gitug01.filmpgraphy.domain.entity.FilmEntity
import com.gitug01.filmpgraphy.domain.repo.FilmRepo

class FilmRepoImpl() : FilmRepo{


    var counter: Int = 0
    var counter02: Int = 0
    var counter03: Int = 0
    var counter04: Int = 0

    private var cache: ArrayList<FilmEntity> = ArrayList()
    private var cacheSoon: ArrayList<FilmEntity> = ArrayList()
    private var cacheTop: ArrayList<FilmEntity> = ArrayList()
    private var cacheNow: ArrayList<FilmEntity> = ArrayList()

    override fun films(): ArrayList<FilmEntity> {
        return cache
    }

    override fun filmsSoon(): ArrayList<FilmEntity> {
        return cacheSoon
    }

    override fun filmsTop(): ArrayList<FilmEntity> {
        return cacheTop
    }

    override fun filmsNow(): ArrayList<FilmEntity> {
        return cacheNow
    }


    override fun addFilm(film: FilmEntity): Int {
        var newId = ++counter
        film.id = newId
        cache.add(film)
        return newId
    }

    override fun addFilmToSoon(film: FilmEntity): Int {
        var newId = ++counter02
        film.id = newId
        cacheSoon.add(film)
        return newId
    }

    override fun addFilmToTop(film: FilmEntity): Int {
        var newId = ++counter03
        film.id = newId
        cacheTop.add(film)
        return newId
    }

    override fun addFilmToNow(film: FilmEntity): Int {
        var newId = ++counter04
        film.id = newId
        cacheNow.add(film)
        return newId
    }

    override fun removeFilm(id: Int) {
        cache.removeAt(id)
    }

    override fun editFilm(id: Int, film: FilmEntity) {
        TODO("Not yet implemented")
    }


}