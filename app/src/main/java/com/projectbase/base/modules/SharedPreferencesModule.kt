package com.projectbase.base.modules

import com.projectbase.base.local.AppPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val sharedPreferencesModule = module {
    single { AppPreferences(androidContext()) }
}
