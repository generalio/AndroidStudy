package com.generlas.coroutinesnetwork

import retrofit2.http.GET

/**
 * description ： TODO:类的作用
 * date : 2025/3/20 14:48
 */
interface API {
    @GET("banner/json")
    suspend fun getBanner(): ResponseResult<List<Banner>>
}