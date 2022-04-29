package com.projectbase.mainapp.application

import androidx.multidex.MultiDexApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import com.projectbase.base.modules.apiModule
import com.projectbase.base.modules.databaseModule
import com.projectbase.base.modules.repositoryModule
import com.projectbase.base.modules.sharedPreferencesModule
import com.projectbase.base.modules.reactivexModule
import com.projectbase.mainapp.mainappModules
import org.koin.core.logger.Level

class MainApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MainApplication)

            koin.loadModules(
                listOf(
                    apiModule,
                    databaseModule,
                    reactivexModule,
                    repositoryModule,
                    sharedPreferencesModule,
                    mainappModules
                )
            )
        }
    }
}
