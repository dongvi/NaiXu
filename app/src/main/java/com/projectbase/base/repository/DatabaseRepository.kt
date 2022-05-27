package com.projectbase.base.repository

import com.projectbase.base.datahandling.Result
import com.projectbase.base.local.database.dao.DailyBlogDao
import com.projectbase.base.local.database.dao.LikeActionDao
import com.projectbase.base.local.database.entity.DailyBlogEntity
import com.projectbase.base.local.database.entity.LikeActionEntity
import com.projectbase.base.ultils.rx.AppReactivexSchedulers
import io.reactivex.Observable

class DatabaseRepository (
    private val dailyBlogDao: DailyBlogDao,
    private val likeActionDao: LikeActionDao,
    private val rxSchedulers: AppReactivexSchedulers
) {
    // daily blog data
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
        }.subscribeOn(rxSchedulers.io()).observeOn(rxSchedulers.androidMainThread())
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
        }.subscribeOn(rxSchedulers.io()).observeOn(rxSchedulers.androidMainThread())
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
        }.subscribeOn(rxSchedulers.io()).observeOn(rxSchedulers.androidMainThread())
    }

    // like action data
    fun getLikeActionDataByUserId(userId: String): Observable<Result<MutableList<String?>>> {
        return Observable.create<Result<MutableList<String?>>> { emitter ->
            try {
                val result = likeActionDao.getListBlogIdByUserId(userId)
                emitter.onNext(Result(result, null))
                emitter.onComplete()
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
                emitter.onError(throwable)
            }
        }.subscribeOn(rxSchedulers.io()).observeOn(rxSchedulers.androidMainThread())
    }

    fun insertLikeActionEntity(data: LikeActionEntity): Observable<Result<Boolean>> {
        return Observable.create<Result<Boolean>> { emitter ->
            try {
                likeActionDao.insertLikeAction(data)
                emitter.onNext(Result(true, null))
                emitter.onComplete()
            } catch (throwable: Throwable) {
                emitter.onError(throwable)
            }
        }.subscribeOn(rxSchedulers.io()).observeOn(rxSchedulers.androidMainThread())
    }

    fun deleteLikeActionEntity(userId: String?, blogId: String?): Observable<Result<Boolean>> {
        return Observable.create<Result<Boolean>> { emitter ->
            try {
                likeActionDao.deleteLikeAction(userId, blogId)
                emitter.onNext(Result(true, null))
                emitter.onComplete()
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
                emitter.onError(throwable)
            }
        }.subscribeOn(rxSchedulers.io()).observeOn(rxSchedulers.androidMainThread())
    }
}
