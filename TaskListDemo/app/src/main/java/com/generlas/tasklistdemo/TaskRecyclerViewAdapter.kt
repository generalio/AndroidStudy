package com.generlas.tasklistdemo

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Visibility
import kotlin.math.max
import kotlin.math.min

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
        fun onExpandClick(position: Int)
        fun onItemClick(position: Int)
        fun onDeleteParentClick(position: Int)
        fun onDeleteChildClick(position: Int)
    }

    private val TYPE_PARENT = 1

    //一级任务的视图绑定及点击事件
    @SuppressLint("ClickableViewAccessibility")
    inner class parentViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val parentTitle: TextView = view.findViewById(R.id.parent_title)
        val parentIsExpand: ImageView = view.findViewById(R.id.parent_isExpand)
        val parentDelete: TextView = view.findViewById(R.id.parent_delete)
        val textLayout: View = view.findViewById(R.id.parent_text_layout)

        init {
            parentIsExpand.setOnClickListener {
                if(getItem(adapterPosition).isExpand) {
                    parentIsExpand.setImageResource(R.drawable.ic_folder)
                } else {
                    parentIsExpand.setImageResource(R.drawable.ic_expand)
                }
                itemClickListener.onExpandClick(adapterPosition)
            }
            textLayout.setOnClickListener {
                //接口回调
                itemClickListener.onItemClick(adapterPosition)
            }

            parentDelete.setOnClickListener {
                itemClickListener.onDeleteParentClick(adapterPosition)
            }
        }
    }

    //二级视图绑定
    inner class childViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val childTitle: TextView = view.findViewById(R.id.child_title)
        val childDelete: TextView = view.findViewById(R.id.child_delete)

        init {
            childDelete.setOnClickListener {
                itemClickListener.onDeleteChildClick(adapterPosition)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val task = getItem(position)
        when(holder) {
            is parentViewHolder -> {
                holder.parentTitle.text = task.text
                holder.parentDelete.translationX = 0F
                holder.textLayout.translationX = 0F
                if(task.isExpand) {
                    holder.parentIsExpand.setImageResource(R.drawable.ic_expand)
                } else {
                    holder.parentIsExpand.setImageResource(R.drawable.ic_folder)
                }
            }

            is childViewHolder -> {
                holder.childTitle.text = task.text
                holder.childTitle.translationX = 0F
                holder.childDelete.translationX = 0F
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

    //返回不同的TYPE
    override fun getItemViewType(position: Int): Int {
        return getItem(position).TYPE
    }

}