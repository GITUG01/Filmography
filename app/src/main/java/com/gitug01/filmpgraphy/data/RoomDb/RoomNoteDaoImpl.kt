package com.gitug01.filmpgraphy.data.RoomDb

import android.content.Context
import androidx.annotation.Nullable
import androidx.room.Room

class RoomNoteRepoImpl(context: Context) : NoteRepo {
    private val noteDao: NoteDao = Room.databaseBuilder(
        context,
        NoteRoomDb::class.java,
        "notes.db"
    ).build().noteDao()

    override fun add(noteEntity: NoteEntity) {
        noteDao.add(noteEntity)
    }

    override fun exist(filmName: String): Boolean {
        return noteDao.exists(filmName)
    }

    @Nullable
    override fun get(filmName: String): NoteEntity {
        return noteDao.getFilmByName(filmName)
    }

    override fun getAllFilms(): List<NoteEntity> {
        return noteDao.getAll()
    }

    override fun delete(filmName: String) {
        TODO("Not yet implemented")
    }

    override fun clear() {
        noteDao.clear()
    }

    override fun update(note: String, name: String) {
        noteDao.update(note, name)
    }

    override fun addOrUpdate(noteEntity: NoteEntity) {
        noteDao.addOrUpdate(noteEntity)
    }

}