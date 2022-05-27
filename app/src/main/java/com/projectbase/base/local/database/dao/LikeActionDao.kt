package com.projectbase.base.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.projectbase.base.local.database.entity.LikeActionEntity

@Dao
interface LikeActionDao {
    @Query("SELECT blogId FROM likeactionentity WHERE userId = :id")
    fun getListBlogIdByUserId(id: String): MutableList<String?>

    @Insert
    fun insertLikeAction(entity: LikeActionEntity)

    @Query("DELETE FROM likeactionentity WHERE userId = :uId AND blogId = :bId")
    fun deleteLikeAction(uId: String?, bId: String?)
}
