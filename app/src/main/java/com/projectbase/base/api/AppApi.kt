package com.projectbase.base.api

import com.projectbase.base.api.model.*
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AppApi {

    @GET("get/example")
    fun getExample(@Query("id") id: Int): Observable<GetResponse>

    @POST("post/example")
    fun postExample(@Body body: PostRequest): Observable<PostResponse>

    @GET("bannerads")
    fun getBannerAds(): Observable<MutableList<BannerAds>>

    @GET("dailyblog")
    fun getDailyBlog(): Observable<MutableList<DailyBlog>>
}
