package com.gitug01.filmpgraphy.data.impl

import com.gitug01.filmpgraphy.domain.entity.FilmEntity
import com.gitug01.filmpgraphy.domain.repo.DBfilmRepo

class WebDBFilmRepoImpl : DBfilmRepo {
    override fun getFilmsForUserSync(userName: String): List<FilmEntity> {
        TODO("Not yet implemented")
    }
}