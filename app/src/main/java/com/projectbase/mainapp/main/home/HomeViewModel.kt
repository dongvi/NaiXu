package com.projectbase.mainapp.main.home

import androidx.lifecycle.MutableLiveData
import com.projectbase.base.api.model.BannerAds
import com.projectbase.base.api.model.Error
import com.projectbase.base.api.model.ItemDailyBlog
import com.projectbase.base.datahandling.ResultsObserver
import com.projectbase.base.repository.ApiRepository
import com.projectbase.base.ui.BaseViewModel
import com.projectbase.base.ultils.extentions.plusAssign

class HomeViewModel(private val appApi: ApiRepository) : BaseViewModel() {
    private val getListBannerAds = MutableLiveData<List<BannerAds>>()
    private val getListDailyBlog = MutableLiveData<List<ItemDailyBlog>>()
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

    private inner class GetBannerAdsApiObserver : ResultsObserver<List<BannerAds>>() {
        override fun onSuccess(listBannerAds: List<BannerAds>) {
            getListBannerAds.postValue(listBannerAds)
        }

        override fun onError(err: Error) {
            error.postValue(err)
        }
    }

    private inner class GetDailyBlogApiObserver : ResultsObserver<List<ItemDailyBlog>>() {
        override fun onSuccess(listDailyBlog: List<ItemDailyBlog>) {
            getListDailyBlog.postValue(listDailyBlog)
        }

        override fun onError(err: Error) {
            error.postValue(err)
        }
    }
}
