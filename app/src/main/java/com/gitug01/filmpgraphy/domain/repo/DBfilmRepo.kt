package com.gitug01.filmpgraphy.domain.repo

import androidx.annotation.WorkerThread
import com.gitug01.filmpgraphy.domain.entity.FilmEntity
import kotlinx.coroutines.Deferred

interface DBFilmRepo {
    @WorkerThread
    fun getFilmsForUserSync(requestCode: String, apiKey: String): List<FilmEntity>

    @WorkerThread
    suspend fun getFilmsForUserAsync(requestCode: String, apiKey: String): List<FilmEntity>
}