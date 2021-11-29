package com.gitug01.filmpgraphy.data.RoomDb

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface NoteDao {
    @Insert(onConflict = REPLACE)
    fun add(noteEntity: NoteEntity)

    @Delete
    fun delete(noteEntity: NoteEntity)

    @Query("SELECT * FROM table_name WHERE film_name = :name")
    fun getFilmByName(name: String): NoteEntity

    @Query("SELECT * FROM table_name")
    fun getAll(): List<NoteEntity>

    @Query("DELETE FROM table_name")
    fun clear()

    @Query("UPDATE table_name SET note = :note WHERE film_name = :name")
    fun update(note: String, name: String)

    @Transaction
    fun addOrUpdate(noteEntity: NoteEntity) {
        if (getFilmByName(noteEntity.filmName) == null) {
            add(noteEntity)
        } else {
            update(noteEntity.note, noteEntity.filmName)
        }
    }
}