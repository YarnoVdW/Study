package com.byyakke.study

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class StudyItemViewModel(application: Application): AndroidViewModel(application) {

    private val context: Context = application.applicationContext
    private val _trickList = MutableLiveData<MutableList<StudyItem>>()
    val trickList: LiveData<MutableList<StudyItem>>
        get() = _trickList
    private lateinit var studyItem: StudyItem


    fun readTrickList() {
        _trickList.postValue(studyItem.getResult())
    }

}