package com.projectbase.mainapp.main

import com.projectbase.mainapp.main.home.dailyblog.dailyBlogModule
import com.projectbase.mainapp.main.home.dailyblog.postblog.postBlogModule
import com.projectbase.mainapp.main.home.homeModule
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules

val mainActivityModule = module {
    viewModel {
        MainViewModel(get())
    }
    loadKoinModules(homeModule)
    loadKoinModules(dailyBlogModule)
    loadKoinModules(postBlogModule)
}
