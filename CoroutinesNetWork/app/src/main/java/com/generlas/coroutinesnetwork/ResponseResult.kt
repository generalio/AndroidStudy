package com.generlas.coroutinesnetwork

import com.google.gson.annotations.SerializedName

/**
 * description ： TODO:类的作用
 * date : 2025/3/20 14:47
 */
data class ResponseResult<T>(
    @SerializedName("errorCode") var errorCode: Int = -1,
    @SerializedName("errorMsg") var errorMsg: String? = "",
    @SerializedName("data") var data: T? = null
)
