package com.projectbase.base.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DailyBlogEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "userId") var userId: String,
    @ColumnInfo(name = "dateSubmitted") var dateSubmitted: String?,
    @ColumnInfo(name = "textBlog") var textBlog: String?,
    @ColumnInfo(name = "imageBlog") var imageBlog: String?
)
