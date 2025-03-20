package com.generlas.coroutinesnetwork

import com.google.gson.annotations.SerializedName

/**
 * description ： TODO:类的作用
 * date : 2025/3/20 14:48
 */
data class Banner(
    @SerializedName("id") var id: Long = 0,
    @SerializedName("title") var title: String = "",
    @SerializedName("desc") var desc: String = "",
    @SerializedName("url") var url: String = ""
)
