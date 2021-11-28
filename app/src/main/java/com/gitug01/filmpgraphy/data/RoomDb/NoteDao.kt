package com.gitug01.filmpgraphy.data.RoomDb

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

interface NoteDao {
    @Insert(onConflict = REPLACE)
    fun add(noteEntity: NoteEntity)
    @Delete
    fun delete(noteEntity: NoteEntity)
    @Query("SELECT * FROM table_name WHERE film_name = :name")
    fun getFilmByName(name: String): NoteEntity
    @Query("DELETE FROM table_name")
    fun clear()
}