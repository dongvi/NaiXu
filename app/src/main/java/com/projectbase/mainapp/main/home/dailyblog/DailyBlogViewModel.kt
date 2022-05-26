package com.projectbase.mainapp.main.home.dailyblog

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.projectbase.base.api.model.Error
import com.projectbase.base.api.model.DailyBlog
import com.projectbase.base.api.model.User
import com.projectbase.base.datahandling.ResultsObserver
import com.projectbase.base.local.database.entity.DailyBlogEntity
import com.projectbase.base.local.database.entity.LikeActionEntity
import com.projectbase.base.repository.ApiRepository
import com.projectbase.base.repository.DatabaseRepository
import com.projectbase.base.ui.BaseViewModel
import com.projectbase.base.ultils.extentions.plusAssign

class DailyBlogViewModel(private val appApi: ApiRepository,private val db: DatabaseRepository) : BaseViewModel() {
    private val dailyBlogLiveData = MutableLiveData<MutableList<DailyBlog>>()
    private val userLiveData = MutableLiveData<MutableList<User>>()
    private val likeActionLiveData = MutableLiveData<MutableList<String?>>()
    private val error = MutableLiveData<Error>()

    fun getListDailyBlog() = dailyBlogLiveData
    fun getListUser() = userLiveData
    fun getListLikeAction() = likeActionLiveData
    fun error() = error

    fun getDailyBlogApi() {
        compositeDisposable += appApi.getDailyBlog().subscribeWith(GetDailyBlogApiObserver())
    }

    fun getAllUserApi() {
        compositeDisposable += appApi.getAllUser().subscribeWith(GetAllUserApiObserver())
    }

    fun getDailyBlogLocal() {
        compositeDisposable += db.getAllDailyBlog().subscribeWith(GetDailyBlogLocalObserver())
    }

    fun getLikeAction(userId: String) {
        compositeDisposable += db.getLikeActionDataByUserId(userId).subscribeWith(GetLikeActionObserver())
    }

    fun insertLikeAction(data: LikeActionEntity) {
        compositeDisposable += db.insertLikeActionEntity(data).subscribeWith(InsertLikeActionObserver())
    }

    fun deleteLikeAction(userId: String?, blogId: String?) {
        compositeDisposable += db.deleteLikeActionEntity(userId, blogId).subscribeWith(DeleteLikeActionObserver())
    }

    private inner class GetDailyBlogApiObserver : ResultsObserver<MutableList<DailyBlog>>() {
        override fun onSuccess(listData: MutableList<DailyBlog>) {
            val currentData = dailyBlogLiveData.value ?: mutableListOf()
            currentData.addAll(listData)
            dailyBlogLiveData.postValue(currentData)
        }

        override fun onError(err: Error) {
            error.postValue(err)
        }
    }

    private inner class GetDailyBlogLocalObserver : ResultsObserver<MutableList<DailyBlogEntity>>() {
        override fun onSuccess(listData: MutableList<DailyBlogEntity>) {
            val dataTransformation = listData.map {
                DailyBlog(it.id,
                    it.userId,
                    it.dateSubmitted,
                    it.textBlog,
                    it.imageBlog)
            } as MutableList<DailyBlog>

            val currentData = dailyBlogLiveData.value ?: mutableListOf()
            currentData.addAll(dataTransformation)
            dailyBlogLiveData.postValue(currentData)
        }

        override fun onError(err: Error) {
            error.postValue(err)
        }
    }

    private inner class GetAllUserApiObserver : ResultsObserver<MutableList<User>>() {
        override fun onSuccess(listUser: MutableList<User>) {
            userLiveData.postValue(listUser)
        }

        override fun onError(err: Error) {
            error.postValue(err)
        }
    }

    private inner class GetLikeActionObserver : ResultsObserver<MutableList<String?>>() {
        override fun onSuccess(listData: MutableList<String?>) {
            likeActionLiveData.postValue(listData)
        }

        override fun onError(err: Error) {
            error.postValue(err)
        }
    }

    private inner class InsertLikeActionObserver : ResultsObserver<Boolean>() {
        override fun onSuccess(success: Boolean) {}

        override fun onError(err: Error) {
            error.postValue(err)
        }
    }

    private inner class DeleteLikeActionObserver : ResultsObserver<Boolean>() {
        override fun onSuccess(success: Boolean) {}

        override fun onError(err: Error) {
            error.postValue(err)
        }
    }
}
