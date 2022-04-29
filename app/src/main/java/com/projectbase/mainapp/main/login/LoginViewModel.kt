package com.projectbase.mainapp.main.login

import androidx.lifecycle.MutableLiveData
import com.projectbase.base.datahandling.ResultsObserver
import com.projectbase.base.api.model.GetResponse
import com.projectbase.base.api.model.Error
import com.projectbase.base.repository.ApiRepository
import com.projectbase.base.ui.BaseViewModel
import com.projectbase.base.ultils.extentions.plusAssign

class LoginViewModel constructor(
    private val appApi: ApiRepository
) : BaseViewModel() {

    private val getResponseData = MutableLiveData<GetResponse>()
    private val error = MutableLiveData<Error>()

    fun getResponseData() = getResponseData
    fun error() = error


    fun getExampleApi(id: Int) {
        compositeDisposable += appApi.getExample(id).subscribeWith(GetExampleApiObserver())
    }

    private inner class GetExampleApiObserver : ResultsObserver<GetResponse>() {
        override fun onSuccess(response: GetResponse) {
            getResponseData.postValue(response)
        }

        override fun onError(err: Error) {
            error.postValue(err)
        }
    }
}
