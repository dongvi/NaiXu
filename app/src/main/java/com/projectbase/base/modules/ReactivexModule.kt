package com.projectbase.base.modules

import org.koin.dsl.module
import com.projectbase.base.ultils.rx.AppReactivexSchedulers

val reactivexModule = module {
    single { AppReactivexSchedulers() }
}
