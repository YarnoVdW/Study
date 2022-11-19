package com.byyakke.study.model

import java.util.*
import com.google.gson.annotations.SerializedName

data class StudyItem(
    var id: Int,
    var description: String,
    @SerializedName("created_at")
    var createdAt: Date) {
    fun clear() {
        id =0
        description = ""
        createdAt = Date()
    }
}

