package com.projectbase.base.ultils.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppReactivexSchedulers {

    fun io(): Scheduler {
        return Schedulers.io()
    }

    fun androidMainThread(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}
