package com.projectbase.base.api.model

import com.squareup.moshi.Json

data class DailyBlog(
    @field:Json(name = "userName")
    var userName: String?,
    @field:Json(name = "avatar")
    var avatar: String?,
    @field:Json(name = "dateSubmitted")
    var dateSubmitted: String?,
    @field:Json(name = "textBlog")
    var textBlog: String?,
    @field:Json(name = "imageBlog")
    var imageBlog: String?
)
