package com.projectbase.base.local.database.dao

import androidx.room.*
import com.projectbase.base.local.database.entity.ExampleEntity

@Dao
interface ExampleDao {
    @Query("SELECT * FROM exampleentity WHERE id LIKE :id LIMIT 1")
    fun getExampleById(id: Int): ExampleEntity?

    /**
     * returns rowId of the newly inserted item
     */
    @Insert
    fun addExampleEntity(entity: ExampleEntity): Long

    /**
     * returns number of updated rows
     */
    @Update
    fun updateExampleEntity(entity: ExampleEntity): Int

    /**
     * returns number of deleted rows
     */
    @Delete
    fun deleteExampleEntity(entity: ExampleEntity): Int
}
