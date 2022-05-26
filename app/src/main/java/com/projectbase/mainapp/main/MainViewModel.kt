package com.projectbase.mainapp.main

import androidx.lifecycle.MutableLiveData
import com.projectbase.base.api.model.User
import com.projectbase.base.repository.ApiRepository
import com.projectbase.base.ui.BaseViewModel

class MainViewModel(
    private val appApi: ApiRepository
) : BaseViewModel() {
    private val user = MutableLiveData<User>()

    fun getUser(): MutableLiveData<User> {
        user.postValue(User("u0", null, null))
        return user
    }
}
