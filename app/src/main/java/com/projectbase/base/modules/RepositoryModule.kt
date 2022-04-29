package com.projectbase.base.modules

import com.projectbase.base.repository.ApiRepository
import com.projectbase.base.repository.DatabaseRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { ApiRepository(get(), get(), get()) }
    single { DatabaseRepository(get(), get()) }
}
