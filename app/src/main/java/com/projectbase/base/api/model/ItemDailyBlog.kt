package com.projectbase.base.api.model

import com.squareup.moshi.Json

data class ItemDailyBlog(
    @field:Json(name = "userId")
    var userId: String?,

    @field:Json(name = "dateSubmitted")
    var dateSubmitted: String?,

    @field:Json(name = "textBlog")
    var textBlog: String?,

    @field:Json(name = "imageBlog")
    var imageBlog: String?
)
