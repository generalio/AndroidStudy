package com.generals.corountinestudy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * @Desc : 类的描述
 * @Author : zzx
 * @Date : 2025/5/15 16:24
 */

class MainViewModel : ViewModel() {

    private val _pagingDataLiveData: MutableLiveData<PagingData<Repo>> = MutableLiveData()

    val pagingDataLiveData : LiveData<PagingData<Repo>> get() = _pagingDataLiveData

    fun getPagingData() {
        viewModelScope.launch {
            val flow =  Pager(config = PagingConfig(50), pagingSourceFactory = {RepoPagingSource(
                GithubService.create()
            )}).flow
            flow.collect { pagingData ->
                _pagingDataLiveData.postValue(pagingData)
            }
        }
    }
}