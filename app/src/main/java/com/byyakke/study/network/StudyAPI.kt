package com.byyakke.study.network

import android.content.Intent
import android.content.SharedPreferences
import com.byyakke.study.Study
import com.byyakke.study.model.*
import com.byyakke.study.ui.login.LoginActivity
import com.byyakke.study.util.MySharedPreferences
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import io.reactivex.internal.operators.observable.ObservableError
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface StudyAPI {
    @Headers("No-Authentication: true")
    @POST("/v1/login")
    fun login(@Body login: Login): Observable<LoginTokenResponse>

    @Headers("No-Authentication: true")
    @POST("/v1/register")
    fun register(@Body signup: Login): Observable<BasicResponse>

    @GET("/v1/study/all")
    fun getAllTask(): Observable<StudyItemListResponse>

    @POST("/v1/study/create")
    fun createTask(@Body basicTask: BasicStudyItem): Observable<StudyItemCreateResponse>

    @PUT("/v1/study/update/{id}")
    fun update(@Body item: BasicStudyItem, @Path("id") id: Int): Observable<StudyItemUpdateResponse>

    @DELETE("/v1/study/delete/{id}")
    fun delete(@Path("id") id:Int): Observable<StudyItemDeleteResponse>

    companion object {
        fun create(): StudyAPI {
            val client: OkHttpClient? = OkHttpClient.Builder()
                .addInterceptor {
                    val header = it.request().header("No-Authentication")
                    val token = MySharedPreferences.getToken()
                    val newRequest =
                        if(header == null) {
                            it.request().newBuilder()
                                .addHeader("Authorization", "Bearer $token")
                                .build()
                        } else {
                            it.request()
                        }
                    it.proceed(newRequest)
                }
                .addInterceptor {
                    val request = it.request()
                    val response = it.proceed(request)
                    val intent = Intent(Study.applicationContext(), LoginActivity::class.java)
                    if(request.header("No-Authentication")==null){
                        when(response.code()) {
                            401 -> {
                                Study.applicationContext().startActivity(intent)
                                MySharedPreferences.clearToken()
                            }
                        }
                    }
                    response
                }
                .build()
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .baseUrl("http://192.168.1.60:8080")
                .build()
            return  retrofit.create(StudyAPI::class.java)

        }
    }
}