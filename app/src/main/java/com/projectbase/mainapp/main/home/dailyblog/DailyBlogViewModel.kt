package com.projectbase.mainapp.main.home.dailyblog

import androidx.lifecycle.MutableLiveData
import com.projectbase.base.api.model.Error
import com.projectbase.base.api.model.ItemDailyBlog
import com.projectbase.base.datahandling.ResultsObserver
import com.projectbase.base.repository.ApiRepository
import com.projectbase.base.ui.BaseViewModel
import com.projectbase.base.ultils.extentions.plusAssign

class DailyBlogViewModel(private val appApi: ApiRepository) : BaseViewModel() {
    private val getListDailyBlog = MutableLiveData<MutableList<ItemDailyBlog>>()
    private val error = MutableLiveData<Error>()

    fun getListDailyBlog() = getListDailyBlog
    fun error() = error

    fun getDailyBlogApi() {
        compositeDisposable += appApi.getDailyBlog().subscribeWith(GetDailyBlogApiObserver())
    }

    private inner class GetDailyBlogApiObserver : ResultsObserver<MutableList<ItemDailyBlog>>() {
        override fun onSuccess(listDailyBlog: MutableList<ItemDailyBlog>) {
            getListDailyBlog.postValue(listDailyBlog)
        }

        override fun onError(err: Error) {
            error.postValue(err)
        }
    }
}