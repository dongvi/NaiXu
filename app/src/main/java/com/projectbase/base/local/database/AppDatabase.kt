package com.projectbase.base.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.projectbase.base.local.database.dao.DailyBlogDao
import com.projectbase.base.local.database.entity.DailyBlogEntity

@Database(entities = [DailyBlogEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dailyBlogDao(): DailyBlogDao
}
