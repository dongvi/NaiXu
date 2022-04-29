package com.projectbase.mainapp.main.login

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginActivityModule = module {
    viewModel {
       LoginViewModel(get())
    }
}
