package com.byyakke.study.repository

import com.byyakke.study.model.*
import com.byyakke.study.network.StudyAPI
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.operators.observable.ObservableError
import io.reactivex.schedulers.Schedulers
import rx.schedulers.Schedulers.io

object Repository {
    private val client by lazy { StudyAPI.create() }

    fun login(username: String, password: String): Observable<LoginTokenResponse> {
        val data = Login(username, password)
        return client.login(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun register(username: String, password: String): Observable<BasicResponse> {
        val data = Login(username, password)
        return client.register(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getAllStudyItems(): Observable<StudyItemListResponse> {
        return client.getAllTask()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun createTask(description: String): Observable<StudyItemCreateResponse>{
        val data = BasicStudyItem(description)
        return client.createTask(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun updateTask(id: Int, description: String): Observable<StudyItemUpdateResponse> {
        val data = BasicStudyItem(description)
        return client.update(data, id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun deleteTask(id: Int): Observable<StudyItemDeleteResponse> {
        return client.delete(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}