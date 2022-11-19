package com.byyakke.study


import android.os.Parcelable
import android.util.Log
import kotlinx.parcelize.Parcelize
import java.sql.Connection
import java.sql.DriverManager


data class StudyItem(var description: String, var numberOfLikes: Int) {

    private val studyItems  = mutableListOf<StudyItem>()
    private lateinit var  connection: Connection


}