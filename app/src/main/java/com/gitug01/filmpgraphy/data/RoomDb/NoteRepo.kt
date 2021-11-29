package com.gitug01.filmpgraphy.data.RoomDb

interface NoteRepo {
    fun add(noteEntity: NoteEntity)
    fun get(filmName: String): NoteEntity
    fun getAllFilms(): List<NoteEntity>
    fun delete(filmName: String)
    fun clear()
    fun update(note: String, name: String)
    fun addOrUpdate(noteEntity: NoteEntity)
}