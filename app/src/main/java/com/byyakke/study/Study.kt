package com.byyakke.study

import android.app.Application
import android.content.Context
class Study: Application() {
    init{
        instance = this
    }
    companion object {
        private var instance: Study? = null
        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }
}