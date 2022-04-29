package com.projectbase.base.api.model

import com.squareup.moshi.Json

data class GetResponse(
    @field:Json(name = "access_token")
    val accessToken: String?
)
