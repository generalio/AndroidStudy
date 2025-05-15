package com.generals.corountinestudy

import com.google.gson.annotations.SerializedName

/**
 * @Desc : 类的描述
 * @Author : zzx
 * @Date : 2025/5/15 15:45
 */

data class Repo(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String?,
    @SerializedName("stargazers_count") val starCount: Int
)

data class RepoResponse(
    @SerializedName("items") val items: List<Repo> = emptyList()
)