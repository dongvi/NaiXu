package com.projectbase.base.local.database.dao

import androidx.room.*
import com.projectbase.base.local.database.entity.DailyBlogEntity


@Dao
interface DailyBlogDao {
    @Query("SELECT * FROM dailyblogentity WHERE id LIKE :id LIMIT 1")
    fun getDailyBlogById(id: Int): DailyBlogEntity?

    /**
    * returns rowId of the newly inserted item
    */
    @Insert
    fun insertDailyBlog(entity: DailyBlogEntity) : Long

    /**
     * returns number of updated rows
     */
    @Update
    fun updateDailyBlog(entity: DailyBlogEntity) : Int

    /**
     * returns number of deleted rows
     */
    @Delete
    fun deleteDailyBlog(entity: DailyBlogEntity) : Int

    @Query("SELECT * FROM dailyblogentity")
    fun getAllDailyBlog(): MutableList<DailyBlogEntity>
}
