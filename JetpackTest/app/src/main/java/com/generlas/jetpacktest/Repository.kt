package com.generlas.jetpacktest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * description ： TODO:类的作用
 * date : 2025/2/16 18:53
 */
object Repository {

    fun getUser(userId: String) : LiveData<User> {
        val liveData = MutableLiveData<User>()
        liveData.value = User(userId, userId, 0)
        return liveData
    }
}