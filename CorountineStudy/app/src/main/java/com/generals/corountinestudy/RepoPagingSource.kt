package com.generals.corountinestudy

import androidx.paging.PagingSource
import androidx.paging.PagingState

/**
 * @Desc : 类的描述
 * @Author : zzx
 * @Date : 2025/5/15 15:59
 */

class RepoPagingSource(private val githubService: GithubService) : PagingSource<Int, Repo>() {
    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        return try {
            val page = params.key ?: 1
            val pageSize = params.loadSize
            val repoResponse = githubService.searchRepos(page, pageSize)
            val repoItems = repoResponse.items
            val preKey = if(page > 1) page - 1 else null
            val nextKey = if(repoItems.isNotEmpty()) page + 1 else null
            LoadResult.Page(repoItems, preKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}