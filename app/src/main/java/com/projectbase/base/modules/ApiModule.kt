package com.projectbase.base.modules

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.projectbase.BuildConfig
import com.projectbase.R
import com.squareup.moshi.Moshi
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import com.projectbase.base.api.AppApi
import com.projectbase.base.api.AppInterceptor
import java.util.concurrent.TimeUnit

const val CACHE_SIZE = 10L * 1024L * 1024L
const val TIME_OUT = 60L
const val CHUCK_MAX_CONTENT_LENGTH = 250000L

val apiModule = module {
    single { provideMoshi() }
    single { provideCallAdapterFactory() }
    single { provideAppInterceptor(androidContext()) }
    single { provideHttpLoggingInterceptor() }
    single { provideHttpCache(androidContext()) }
    single { provideOkHttpClient(androidContext(), get(), get(), get()) }
    single { provideRetrofit(androidContext(), get(), get()) }
    single { provideUserApi(get()) }
}

fun provideMoshi(): Moshi = Moshi.Builder().build()

fun provideCallAdapterFactory(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

fun provideAppInterceptor(
    context: Context
): AppInterceptor = AppInterceptor(context)

fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val loggingInterceptor = HttpLoggingInterceptor()
    if (BuildConfig.DEBUG) {
        loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    } else {
        loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
    }
    return loggingInterceptor
}

fun provideHttpCache(context: Context): Cache = Cache(context.cacheDir, CACHE_SIZE)

fun provideOkHttpClient(
    context: Context,
    cache: Cache,
    appInterceptor: AppInterceptor,
    loggingInterceptor: HttpLoggingInterceptor,
): OkHttpClient {
    val builder =  OkHttpClient.Builder()
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
        .cache(cache)
        .addInterceptor(appInterceptor)
        .addInterceptor(loggingInterceptor)

    if (BuildConfig.DEBUG) {
        val collector = ChuckerCollector(
            context = context,
            showNotification = true,
            retentionPeriod = RetentionManager.Period.ONE_HOUR
        )
        val chuckInterceptor = ChuckerInterceptor.Builder(context)
            .collector(collector)
            .maxContentLength(CHUCK_MAX_CONTENT_LENGTH)
            .build()
        builder.addInterceptor(chuckInterceptor)
    }

    return builder.build()
}

fun provideRetrofit(
    context: Context,
    moshi: Moshi,
    okHttpClient: OkHttpClient
): Retrofit =
    Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(context.getString(R.string.base_url))
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

fun provideUserApi(retrofit: Retrofit): AppApi = retrofit.create(AppApi::class.java)
