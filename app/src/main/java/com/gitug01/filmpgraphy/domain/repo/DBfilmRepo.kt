package com.gitug01.filmpgraphy.domain.repo

import androidx.annotation.WorkerThread
import com.gitug01.filmpgraphy.domain.entity.FilmEntity

interface DBFilmRepo {
    @WorkerThread
    fun getFilmsForUserSync(requestCode: String): List<FilmEntity>
}