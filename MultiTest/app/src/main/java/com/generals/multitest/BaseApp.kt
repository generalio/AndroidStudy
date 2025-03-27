package com.generals.multitest

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

/**
 * description ： TODO:类的作用
 * date : 2025/3/27 09:21
 */
class BaseApp : Application() {

    private val isDebug = true

    override fun onCreate() {
        super.onCreate()
        if(isDebug) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }

}