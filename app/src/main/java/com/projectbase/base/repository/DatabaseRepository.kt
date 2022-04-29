package com.projectbase.base.repository

import com.projectbase.base.datahandling.Result
import com.projectbase.base.local.database.dao.ExampleDao
import com.projectbase.base.local.database.entity.ExampleEntity
import com.projectbase.base.ultils.rx.AppReactivexSchedulers
import io.reactivex.Observable

class DatabaseRepository(
    private val exampleDao: ExampleDao,
    private val rxSchedulers: AppReactivexSchedulers
) {
    fun getExampleById(id: Int): Observable<Result<ExampleEntity?>> {
        return return Observable.create<Result<ExampleEntity?>> { emitter ->
            try {
                val data = exampleDao.getExampleById(id)
                emitter.onNext(Result(data, null))
                emitter.onComplete()
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
                emitter.onError(throwable)
            }
        }.subscribeOn(rxSchedulers.io())
            .observeOn(rxSchedulers.androidMainThread())
    }

    fun addExampleEntity(data: ExampleEntity): Observable<Result<Boolean>> {
        return Observable.create<Result<Boolean>> { emitter ->
            try {
                exampleDao.addExampleEntity(data)
                emitter.onNext(Result(true, null))
                emitter.onComplete()
            } catch (throwable: Throwable) {
                emitter.onError(throwable)
            }
        }.subscribeOn(rxSchedulers.io())
            .observeOn(rxSchedulers.androidMainThread())
    }

    fun updateExampleEntity(data: ExampleEntity): Observable<Result<Boolean>> {
        return Observable.create<Result<Boolean>> { emitter ->
            try {
                val result = exampleDao.updateExampleEntity(data)
                emitter.onNext(Result(result > 0, null))
                emitter.onComplete()
            } catch (throwable: Throwable) {
                emitter.onError(throwable)
            }
        }.subscribeOn(rxSchedulers.io())
            .observeOn(rxSchedulers.androidMainThread())
    }

    fun deleteExampleEntity(data: ExampleEntity): Observable<Result<Boolean>> {
        return Observable.create<Result<Boolean>> { emitter ->
            try {
                val result = exampleDao.deleteExampleEntity(data)
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
