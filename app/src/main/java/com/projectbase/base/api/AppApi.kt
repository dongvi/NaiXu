package com.projectbase.base.api

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import com.projectbase.base.api.model.PostRequest
import com.projectbase.base.api.model.GetResponse
import com.projectbase.base.api.model.PostResponse

interface AppApi {

    @GET("get/example")
    fun getExample(@Query("id") id: Int): Observable<GetResponse>

    @POST("post/example")
    fun postExample(@Body body: PostRequest): Observable<PostResponse>

}
