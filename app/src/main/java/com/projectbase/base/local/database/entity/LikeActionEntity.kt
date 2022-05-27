package com.projectbase.base.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LikeActionEntity (
    @PrimaryKey val id: String,
    @ColumnInfo(name = "userId") val userId: String,
    @ColumnInfo(name = "blogId") val blogId: String
)
