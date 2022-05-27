package com.projectbase.base.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.projectbase.base.local.database.dao.DailyBlogDao
import com.projectbase.base.local.database.dao.LikeActionDao
import com.projectbase.base.local.database.entity.DailyBlogEntity
import com.projectbase.base.local.database.entity.LikeActionEntity

@Database(entities = [DailyBlogEntity::class, LikeActionEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dailyBlogDao(): DailyBlogDao
    abstract fun likeActionDao(): LikeActionDao
}
