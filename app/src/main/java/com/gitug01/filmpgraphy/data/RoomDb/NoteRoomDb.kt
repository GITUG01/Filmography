package com.gitug01.filmpgraphy.data.RoomDb

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [NoteEntity::class],
    version = 2
)
abstract class NoteRoomDb: RoomDatabase(){
    abstract fun noteDao(): NoteDao
}