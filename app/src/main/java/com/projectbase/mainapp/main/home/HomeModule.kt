package com.projectbase.mainapp.main.home

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val homeModule = module {
    scope(named<HomeFragment>()) {
        viewModel {
            HomeViewModel(get())
        }
    }
}
