package com.generlas.retrofittest

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

/**
 * description ： TODO:类的作用
 * date : 2025/2/23 16:19
 */
object ServiceCreator {

    private const val BASE_URL = "https://www.wanandroid.com/"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    /*
    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)
     */

    inline fun <reified  T> create(): T = retrofit.create(T::class.java)
}