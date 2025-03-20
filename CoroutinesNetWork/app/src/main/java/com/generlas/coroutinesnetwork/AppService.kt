package com.generlas.coroutinesnetwork

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

/**
 * description ： TODO:类的作用
 * date : 2025/3/20 14:52
 */
object AppService {

    private const val BASE_URL = "https://www.wanandroid.com/"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    inline fun <reified T> create(): T = retrofit.create(T::class.java)

}