package com.projectbase.mainapp.main.home

import androidx.lifecycle.MutableLiveData
import com.projectbase.base.api.model.BannerAds
import com.projectbase.base.api.model.Error
import com.projectbase.base.api.model.DailyBlog
import com.projectbase.base.datahandling.ResultsObserver
import com.projectbase.base.repository.ApiRepository
import com.projectbase.base.ui.BaseViewModel
import com.projectbase.base.ultils.extentions.plusAssign

class HomeViewModel(private val appApi: ApiRepository) : BaseViewModel() {
    private val getListBannerAds = MutableLiveData<MutableList<BannerAds>>()
    private val getListDailyBlog = MutableLiveData<MutableList<DailyBlog>>()
    private val error = MutableLiveData<Error>()

    fun getListBannerAds() = getListBannerAds
    fun getListDailyBlog() = getListDailyBlog
    fun error() = error

    fun getBannerAdsApi() {
        compositeDisposable += appApi.getBannerAds().subscribeWith(GetBannerAdsApiObserver())
    }

    fun getDailyBlogApi() {
        compositeDisposable += appApi.getDailyBlog().subscribeWith(GetDailyBlogApiObserver())
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
            getListDailyBlog.postValue(listDailyBlog)
        }

        override fun onError(err: Error) {
            error.postValue(err)
        }
    }
}
