package com.gitug01.filmpgraphy.domain.repo

import com.gitug01.filmpgraphy.R
import com.gitug01.filmpgraphy.domain.entity.FilmEntity

interface FilmRepo {

    fun films(): ArrayList<FilmEntity>
    fun filmsSoon(): ArrayList<FilmEntity>
    fun filmsTop(): ArrayList<FilmEntity>
    fun filmsNow(): ArrayList<FilmEntity>
    fun addFilm(film: FilmEntity): Int
    fun addFilmToSoon(film: FilmEntity): Int
    fun addFilmToTop(film: FilmEntity): Int
    fun addFilmToNow(film: FilmEntity): Int
    fun removeFilm(id: Int)
    fun editFilm(id: Int, film: FilmEntity)

}