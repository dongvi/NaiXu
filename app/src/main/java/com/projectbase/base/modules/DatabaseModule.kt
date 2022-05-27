package com.projectbase.base.modules

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.projectbase.base.local.database.AppDatabase
import org.koin.dsl.module
import org.koin.android.ext.koin.androidContext

const val APP_DB_NAME = "app_database"

val databaseModule = module {
    single { provideRoomDb(androidContext()) }
    single { provideDailyBlogDao(get()) }
    single { provideLikeActionDao(get()) }
}

fun provideRoomDb(context: Context) =
    Room.databaseBuilder(context, AppDatabase::class.java, APP_DB_NAME)
        .addMigrations(MIGRATION_1_2)
        .build()

fun provideDailyBlogDao(db: AppDatabase) = db.dailyBlogDao()

fun provideLikeActionDao(db: AppDatabase) = db.likeActionDao()

// Database migration
private val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        val sql = "CREATE TABLE `LikeActionEntity`( " +
                "`id` TEXT NOT NULL, " +
                "`usedId` TEXT NOT NULL, " +
                "`blogId` TEXT NOT NULL," +
                "PRIMARY KEY (`id`)" +
                ")"
        database.execSQL(sql)
    }
}

/*private val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE `SearchHistory` (`key_word` TEXT NOT NULL, `update_at` INTEGER NOT NULL, PRIMARY KEY(`key_word`))")
    }
}

private val MIGRATION_3_4 = object : Migration(3, 4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE History ADD COLUMN history INTEGER")
    }
}*/
