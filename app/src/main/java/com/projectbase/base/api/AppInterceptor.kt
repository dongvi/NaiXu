package com.projectbase.base.api

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response
import com.projectbase.base.ultils.extentions.getDeviceId

class AppInterceptor(
    private val context: Context
) : Interceptor {

    /**
     * This class define header of api, please confirm with server to define header of api
     */

    override fun intercept(chain: Interceptor.Chain): Response {
        val initRequest = chain.request()
        val deviceId = context.getDeviceId()

        val request = initRequest.newBuilder()
            .addHeader("CLIENT_OS", "android")
            //.addHeader("APP_VERSION_NAME", BuildConfig.VERSION_NAME)
            //.addHeader("APP_VERSION_CODE", "${BuildConfig.VERSION_CODE}")
            .addHeader("DEVICE_ID", deviceId)
            .build()
        return chain.proceed(request)
    }
}
