package com.generlas.coroutinesnetwork

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * description ： TODO:类的作用
 * date : 2025/3/20 14:55
 */
class BannerViewModel: ViewModel() {
    val bannerViewModel: LiveData<List<Banner>> get() = _bannerLiveData
    private val _bannerLiveData = MutableLiveData<List<Banner>>()

    fun getBanner() {
        viewModelScope.launch {
            val res = try {
                withContext(Dispatchers.IO) {
                    BannerNetWork.getBanner()
                }
            }catch (e: Exception) {
                null
            }
            if(res != null && res.errorCode == 0 && res.data != null) {
                _bannerLiveData.postValue(res.data)
            }
        }
    }
}