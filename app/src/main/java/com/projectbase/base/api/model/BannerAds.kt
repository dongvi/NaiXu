package com.projectbase.base.api.model

import com.squareup.moshi.Json

data class BannerAds(
    @field:Json(name = "targetUrl")
    val targetUrl: String?,
    @field:Json(name = "imageUrl")
    val imageUrl: String?
)
