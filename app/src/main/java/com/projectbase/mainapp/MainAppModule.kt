package com.projectbase.mainapp

import com.projectbase.mainapp.main.login.loginActivityModule
import com.projectbase.mainapp.main.mainActivityModule
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val mainappModules = module {
    loadKoinModules(loginActivityModule)
    loadKoinModules(mainActivityModule)
}
