package com.gitug01.filmpgraphy.data.net

import android.app.Application
import android.content.Context
import com.gitug01.filmpgraphy.data.RoomDb.NoteRepo
import com.gitug01.filmpgraphy.data.RoomDb.RoomNoteRepoImpl
import com.gitug01.filmpgraphy.data.retrofit.RetrofitDBFilmRepoImpl
import com.gitug01.filmpgraphy.domain.repo.DBFilmRepo

class App : Application() {
    val DBFilmRepo: DBFilmRepo by lazy { RetrofitDBFilmRepoImpl() }
    val noteRepo: NoteRepo by lazy { RoomNoteRepoImpl(this) }
}


val Context.apps
    get() = applicationContext as App