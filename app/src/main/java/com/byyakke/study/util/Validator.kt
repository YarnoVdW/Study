package com.byyakke.study.util

import com.byyakke.study.R
import com.byyakke.study.Study
import com.google.android.material.textfield.TextInputLayout

class Validator {
    var validations = mutableListOf<TextInputLayout>()

    fun add(value: TextInputLayout): Validator{
        validations.add(value)
        return this
    }
    private fun validateForNotEmpty(): Boolean{
        val check = validations.filter {
            it.editText?.text.isNullOrEmpty()
        }
        check.map {
            it.error = Study.applicationContext()
                .resources.getString(R.string.error_empty)
        }
        return check.isEmpty()
    }

    fun result(): Boolean{
        validations.map {
            it.error = null }
        return  validateForNotEmpty()

    }
}