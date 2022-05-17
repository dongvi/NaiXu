package com.projectbase.mainapp.main.home.dailyblog

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dailyBlogModule = module {
    scope(named<DailyBlogFragment>()) {
        viewModel {
            DailyBlogViewModel(get())
        }
    }
}