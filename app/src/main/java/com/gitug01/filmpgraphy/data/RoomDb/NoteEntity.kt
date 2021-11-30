package com.gitug01.filmpgraphy.data.RoomDb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "table_name", indices = [Index(value = ["film_name"], unique = true)])
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "film_name")
    val filmName: String,

    val note: String
)