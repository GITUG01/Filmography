package com.gitug01.filmpgraphy.domain.repo

import com.gitug01.filmpgraphy.R
import com.gitug01.filmpgraphy.domain.entity.FilmEntity

interface FilmRepo {

    fun films(): ArrayList<FilmEntity>
    fun addFilm(film: FilmEntity): Int
    fun removeFilm(id: Int)
    fun editFilm(id: Int, film: FilmEntity)

}