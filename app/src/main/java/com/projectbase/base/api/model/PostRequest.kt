package com.projectbase.base.api.model

import com.squareup.moshi.Json

data class PostRequest(
    @field:Json(name = "app_version")
    val appVersion: String,
    @field:Json(name = "device_id")
    val deviceId: Int?,
    @field:Json(name = "device_nm")
    val deviceName: String?
)
