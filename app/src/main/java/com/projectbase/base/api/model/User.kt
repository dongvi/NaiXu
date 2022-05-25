package com.projectbase.base.api.model

import com.squareup.moshi.Json

data class User (
    @field: Json(name = "id") val id: String?,
    @field: Json(name = "userName") var userName: String?,
    @field: Json(name = "avatar") var avatar: String?
)
