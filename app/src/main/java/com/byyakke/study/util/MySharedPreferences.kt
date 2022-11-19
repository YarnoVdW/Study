package com.byyakke.study.util

import android.content.Context
import android.content.SharedPreferences
import com.byyakke.study.Study
import com.byyakke.study.network.StudyAPI

object MySharedPreferences {
    private val sharedPreferences: SharedPreferences =
        Study.applicationContext()
            .getSharedPreferences("study", Context.MODE_PRIVATE)


    fun getToken(): String? =
        sharedPreferences.getString("token", "")

    fun saveToken(token: String) =
        sharedPreferences.edit().putString("token", token).apply()

    fun clearToken() =
        sharedPreferences.edit().putString("token", "").apply()
}