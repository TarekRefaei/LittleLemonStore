package com.tarekrefaei.littlelemonstore.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MenuEntity::class], version = 1)
abstract class MenuDataBase : RoomDatabase() {
    abstract val dao : MenuDBDao
}