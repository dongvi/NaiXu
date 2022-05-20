package com.projectbase.mainapp.main.home

import androidx.lifecycle.MutableLiveData
import com.projectbase.base.api.model.BannerAds
import com.projectbase.base.api.model.DailyBlog
import com.projectbase.base.api.model.Error
import com.projectbase.base.api.model.User
import com.projectbase.base.datahandling.ResultsObserver
import com.projectbase.base.local.database.entity.DailyBlogEntity
import com.projectbase.base.repository.ApiRepository
import com.projectbase.base.repository.DatabaseRepository
import com.projectbase.base.ui.BaseViewModel
import com.projectbase.base.ultils.extentions.plusAssign

class HomeViewModel(
    private val appApi: ApiRepository,
    private val db: DatabaseRepository
) : BaseViewModel() {

    private val getListBannerAds = MutableLiveData<MutableList<BannerAds>>()
    private val getListDailyBlogApi = MutableLiveData<MutableList<DailyBlog>>()
    private val getListDailyBlogLocal = MutableLiveData<MutableList<DailyBlogEntity>>()
    private val getListUserApi = MutableLiveData<MutableList<User>>()
    private val getUserByIdApi = MutableLiveData<User>()
    private val error = MutableLiveData<Error>()

    fun getListBannerAds() = getListBannerAds
    fun getListDailyBlogApi() = getListDailyBlogApi
    fun getListDailyBlogLocal() = getListDailyBlogLocal
    fun getUserByIdApi(id: String) = getUserByIdApi
    fun getListUserApi() = getListUserApi

    fun error() = error

    fun getBannerAdsApi() {
        compositeDisposable += appApi.getBannerAds().subscribeWith(GetBannerAdsApiObserver())
    }

    fun getDailyBlogApi() {
        compositeDisposable += appApi.getDailyBlog().subscribeWith(GetDailyBlogApiObserver())
    }

    fun getDailyBlogLocal() {
        compositeDisposable += db.getAllDailyBlog().subscribeWith(GetDailyBlogLocalObserver())
    }

    fun insertDailyBlogLocal(data: DailyBlogEntity) {
        compositeDisposable += db.insertDailyBlogEntity(data).subscribeWith(InsertDailyBlogLocalObserver())
    }

    fun getAllUserApi() {
        compositeDisposable += appApi.getAllUser().subscribeWith(GetListUserApiObserver())
    }

    fun getUserById(id: String) {
        compositeDisposable += appApi.getUserById(id).subscribeWith(GetUserByIdApiObserver())
    }

    private inner class GetBannerAdsApiObserver : ResultsObserver<MutableList<BannerAds>>() {
        override fun onSuccess(listBannerAds: MutableList<BannerAds>) {
            getListBannerAds.postValue(listBannerAds)
        }

        override fun onError(err: Error) {
            error.postValue(err)
        }
    }

    private inner class GetDailyBlogApiObserver : ResultsObserver<MutableList<DailyBlog>>() {
        override fun onSuccess(listDailyBlog: MutableList<DailyBlog>) {
            getListDailyBlogApi.postValue(listDailyBlog)
        }

        override fun onError(err: Error) {
            error.postValue(err)
        }
    }

    private inner class GetDailyBlogLocalObserver : ResultsObserver<MutableList<DailyBlogEntity>>() {
        override fun onSuccess(listDailyBlog: MutableList<DailyBlogEntity>) {
            getListDailyBlogLocal.postValue(listDailyBlog)
        }

        override fun onError(err: Error) {
            error.postValue(err)
        }
    }

    private inner class InsertDailyBlogLocalObserver : ResultsObserver<Boolean>() {
        override fun onSuccess(isInserted: Boolean) {}

        override fun onError(err: Error) {
            error.postValue(err)
        }
    }

    private inner class GetListUserApiObserver() : ResultsObserver<MutableList<User>>() {
        override fun onSuccess(listUser: MutableList<User>) {
            getListUserApi.postValue(listUser)
        }

        override fun onError(err: Error) {
            error.postValue(err)
        }
    }

    private inner class GetUserByIdApiObserver() : ResultsObserver<User>() {
        override fun onSuccess(user: User) {
            getUserByIdApi.postValue(user)
        }

        override fun onError(err: Error) {
            error.postValue(err)
        }
    }
}
