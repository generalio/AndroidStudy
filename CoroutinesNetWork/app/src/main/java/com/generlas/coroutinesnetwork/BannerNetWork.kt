package com.generlas.coroutinesnetwork

/**
 * description ： TODO:类的作用
 * date : 2025/3/20 15:22
 */
object BannerNetWork {
    val appService = AppService.create<API>()

    suspend fun getBanner() = appService.getBanner()
}