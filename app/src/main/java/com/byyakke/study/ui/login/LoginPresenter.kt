package com.byyakke.study.ui.login

interface LoginPresenter {
    fun login(username: String, password: String)
    fun onSucces()
    fun onError(code: Int, message: Int)


}
