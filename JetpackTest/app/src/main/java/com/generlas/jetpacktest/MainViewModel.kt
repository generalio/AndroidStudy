package com.generlas.jetpacktest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.switchMap

/**
 * description ： TODO:类的作用
 * date : 2025/2/12 16:24
 */
class MainViewModel(countReserved: Int) : ViewModel() {

    val counter: LiveData<Int>
        get() = _counter
    private val _counter = MutableLiveData<Int>()

    var userLiveData = MutableLiveData<User>()
    val username: LiveData<String> = userLiveData.map { user ->
        "${user.firstName} ${user.lastName}"
    }

    init {
        _counter.value = countReserved
    }

    fun plusOne() {
        val count = _counter.value ?: 0
        _counter.value = count + 1
    }

    fun clear() {
        _counter.value = 0
    }

    val userIdLiveData = MutableLiveData<String>()
    val users: LiveData<User> = userIdLiveData.switchMap { userId ->
        Repository.getUser(userId)
    }
    fun getUser(userId: String) {
        userIdLiveData.value = userId
    }
}