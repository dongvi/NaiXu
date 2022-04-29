package com.projectbase.base.api.model

import com.squareup.moshi.Json

data class PostResponse(
    @field:Json(name = "result_code")
    val resultCode: Int?,
    @field:Json(name = "result_message")
    val resultMessage: String?
)
