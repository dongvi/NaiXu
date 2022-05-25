package com.projectbase.base.repository

import com.projectbase.base.datahandling.Result
import com.projectbase.base.local.database.dao.DailyBlogDao
import com.projectbase.base.local.database.entity.DailyBlogEntity
import com.projectbase.base.ultils.rx.AppReactivexSchedulers
import io.reactivex.Observable

class DatabaseRepository (
    private val dailyBlogDao: DailyBlogDao,
    private val rxSchedulers: AppReactivexSchedulers
) {
    fun getAllDailyBlog(): Observable<Result<MutableList<DailyBlogEntity>>> {
        return Observable.create<Result<MutableList<DailyBlogEntity>>> { emitter ->
            try {
                val data = dailyBlogDao.getAllDailyBlog()
                emitter.onNext(Result(data, null))
                emitter.onComplete()
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
                emitter.onError(throwable)
            }
        }.subscribeOn(rxSchedulers.io()).observeOn(rxSchedulers.androidMainThread())
    }

    fun insertDailyBlogEntity(data: DailyBlogEntity): Observable<Result<Boolean>> {
        return Observable.create<Result<Boolean>> { emitter ->
            try {
                dailyBlogDao.insertDailyBlog(data)
                emitter.onNext(Result(true, null))
                emitter.onComplete()
            } catch (throwable: Throwable) {
                emitter.onError(throwable)
            }
        }.subscribeOn(rxSchedulers.io())
            .observeOn(rxSchedulers.androidMainThread())
    }

    fun updateDailyBlogEntity(data: DailyBlogEntity): Observable<Result<Boolean>> {
        return Observable.create<Result<Boolean>> { emitter ->
            try {
                val result = dailyBlogDao.updateDailyBlog(data)
                emitter.onNext(Result(result > 0, null))
                emitter.onComplete()
            } catch (throwable: Throwable) {
                emitter.onError(throwable)
            }
        }.subscribeOn(rxSchedulers.io())
            .observeOn(rxSchedulers.androidMainThread())
    }

    fun deleteDailyBlogEntity(data: DailyBlogEntity): Observable<Result<Boolean>> {
        return Observable.create<Result<Boolean>> { emitter ->
            try {
                val result = dailyBlogDao.deleteDailyBlog(data)
                emitter.onNext(Result(result > 0, null))
                emitter.onComplete()
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
                emitter.onError(throwable)
            }
        }.subscribeOn(rxSchedulers.io())
            .observeOn(rxSchedulers.androidMainThread())
    }
}
