package com.projectbase.mainapp.main.home.dailyblog.postblog

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val postBlogModule = module {
    scope(named<PostBlogFragment>()) {
        viewModel {
            PostBlogViewModel(get())
        }
    }
}
