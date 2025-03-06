package com.generlas.recyclerviewstudy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * description ： TODO:类的作用
 * date : 2025/3/6 11:13
 */
class TaskRecyclerViewAdapter(private val itemClickListener: OnItemClickListener) : ListAdapter<TaskInfo, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<TaskInfo>() {
    override fun areContentsTheSame(oldItem: TaskInfo, newItem: TaskInfo): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: TaskInfo, newItem: TaskInfo): Boolean {
        return oldItem == newItem
    }

}) {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private val TYPE_PARENT = 1
    private val TYPE_CHILD = 2

    inner class parentViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val parentTitle: TextView = view.findViewById(R.id.parent_title)
        val parentIsExpand: ImageView = view.findViewById(R.id.parent_isExpand)
        init {
            parentIsExpand.setOnClickListener {
                if(getItem(adapterPosition).isExpand) {
                    parentIsExpand.setImageResource(R.drawable.ic_folder)
                } else {
                    parentIsExpand.setImageResource(R.drawable.ic_expand)
                }
                itemClickListener.onItemClick(adapterPosition)
            }
        }
    }

    inner class childViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val childTitle: TextView = view.findViewById(R.id.child_title)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val task = getItem(position)
        when(holder) {
            is parentViewHolder -> {
                holder.parentTitle.text = task.text
                if(task.isExpand) {
                    holder.parentIsExpand.setImageResource(R.drawable.ic_expand)
                } else {
                    holder.parentIsExpand.setImageResource(R.drawable.ic_folder)
                }
            }

            is childViewHolder -> {
                holder.childTitle.text = task.text
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == TYPE_PARENT) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_parent, parent, false)
            return parentViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_child, parent, false)
            return childViewHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).TYPE
    }

}