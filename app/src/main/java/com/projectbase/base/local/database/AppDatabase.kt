package com.projectbase.base.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.projectbase.base.local.database.dao.ExampleDao
import com.projectbase.base.local.database.entity.ExampleEntity

@Database(entities = [ExampleEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exampleDao(): ExampleDao
}
