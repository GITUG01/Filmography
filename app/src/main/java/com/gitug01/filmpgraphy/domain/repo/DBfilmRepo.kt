package com.gitug01.filmpgraphy.domain.repo

import androidx.annotation.WorkerThread
import com.gitug01.filmpgraphy.domain.entity.FilmEntity

interface DBfilmRepo {
    @WorkerThread
    fun getFilmsForUserSync(userName: String): List<FilmEntity>
}