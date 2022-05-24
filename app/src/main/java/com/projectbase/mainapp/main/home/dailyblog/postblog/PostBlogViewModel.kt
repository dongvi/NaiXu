package com.projectbase.mainapp.main.home.dailyblog.postblog

import androidx.lifecycle.MutableLiveData
import com.projectbase.base.api.model.Error
import com.projectbase.base.datahandling.ResultsObserver
import com.projectbase.base.local.database.entity.DailyBlogEntity
import com.projectbase.base.repository.DatabaseRepository
import com.projectbase.base.ui.BaseViewModel
import com.projectbase.base.ultils.extentions.plusAssign

class PostBlogViewModel(private val db: DatabaseRepository) : BaseViewModel() {

    private val error = MutableLiveData<Error>()

    fun error() = error

    fun insertDailyBlogLocal(data: DailyBlogEntity) {
        compositeDisposable += db.insertDailyBlogEntity(data).subscribeWith(InsertDailyBlogLocalObserver())
    }

    private inner class InsertDailyBlogLocalObserver : ResultsObserver<Boolean>() {
        override fun onSuccess(isInserted: Boolean) {}

        override fun onError(err: Error) {
            error.postValue(err)
        }
    }
}
