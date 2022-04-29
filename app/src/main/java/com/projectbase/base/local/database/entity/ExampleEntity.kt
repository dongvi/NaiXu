package com.projectbase.base.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExampleEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "data") var data: String?
)
