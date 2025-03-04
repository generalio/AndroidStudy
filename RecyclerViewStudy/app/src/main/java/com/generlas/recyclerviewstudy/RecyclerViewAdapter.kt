package com.generlas.recyclerviewstudy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * description ： TODO:类的作用
 * date : 2025/3/4 16:57
 */
class RecyclerViewAdapter(private val list: List<ListInfo>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val text1: TextView = view.findViewById(R.id.item_text1)
        val text2: TextView = view.findViewById(R.id.item_text2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text1.text = list[position].text1
        holder.text2.text = list[position].text2
    }

    override fun getItemCount(): Int {
        return list.size
    }
}