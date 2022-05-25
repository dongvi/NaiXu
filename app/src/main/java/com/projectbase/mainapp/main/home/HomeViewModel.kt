package com.projectbase.mainapp.main.home

import androidx.lifecycle.MutableLiveData
import com.projectbase.base.api.model.BannerAds
import com.projectbase.base.api.model.DailyBlog
import com.projectbase.base.api.model.Error
import com.projectbase.base.api.model.User
import com.projectbase.base.datahandling.ResultsObserver
import com.projectbase.base.repository.ApiRepository
import com.projectbase.base.ui.BaseViewModel
import com.projectbase.base.ultils.extentions.plusAssign

class HomeViewModel(private val appApi: ApiRepository) : BaseViewModel() {

    private val bannerAdsLiveData = MutableLiveData<MutableList<BannerAds>>()
    private val dailyBlogLiveData = MutableLiveData<MutableList<DailyBlog>>()
    private val userLiveData = MutableLiveData<MutableList<User>>()
    private val error = MutableLiveData<Error>()

    fun getListBannerAds() = bannerAdsLiveData
    fun getListDailyBlogApi() = dailyBlogLiveData
    fun getListUserApi() = userLiveData
    fun error() = error

    fun getBannerAdsApi() {
        compositeDisposable += appApi.getBannerAds().subscribeWith(GetBannerAdsApiObserver())
    }

    fun getDailyBlogApi() {
        compositeDisposable += appApi.getDailyBlog().subscribeWith(GetDailyBlogApiObserver())
    }

    fun getAllUserApi() {
        compositeDisposable += appApi.getAllUser().subscribeWith(GetListUserApiObserver())
    }

    private inner class GetBannerAdsApiObserver : ResultsObserver<MutableList<BannerAds>>() {
        override fun onSuccess(listBannerAds: MutableList<BannerAds>) {
            bannerAdsLiveData.postValue(listBannerAds)
        }

        override fun onError(err: Error) {
            error.postValue(err)
        }
    }

    private inner class GetDailyBlogApiObserver : ResultsObserver<MutableList<DailyBlog>>() {
        override fun onSuccess(listDailyBlog: MutableList<DailyBlog>) {
            dailyBlogLiveData.postValue(listDailyBlog)
        }

        override fun onError(err: Error) {
            error.postValue(err)
        }
    }

    private inner class GetListUserApiObserver() : ResultsObserver<MutableList<User>>() {
        override fun onSuccess(listUser: MutableList<User>) {
            userLiveData.postValue(listUser)
        }

        override fun onError(err: Error) {
            error.postValue(err)
        }
    }
}
