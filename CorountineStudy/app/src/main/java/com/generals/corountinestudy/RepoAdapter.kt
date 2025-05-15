package com.generals.corountinestudy

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * @Desc : paging3的adapter
 * @Author : zzx
 * @Date : 2025/5/15 16:33
 */

//通过adapter.submitData(pagingData)提交数据
class RepoAdapter : PagingDataAdapter<Repo, RepoAdapter.ViewHolder>(object : DiffUtil.ItemCallback<Repo>() {
    override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
        return oldItem.id == newItem.id
    }

}){

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

    }
}