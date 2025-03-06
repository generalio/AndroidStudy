package com.generlas.recyclerviewstudy

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity2 : AppCompatActivity(), TaskRecyclerViewAdapter.OnItemClickListener {

    lateinit var recyclerview: RecyclerView
    lateinit var recyclerViewAdapter: TaskRecyclerViewAdapter
    private val taskInfoList: MutableList<TaskInfo> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        recyclerview = findViewById(R.id.rv)

        taskInfoList.add(TaskInfo("1.do homework", 1, mutableListOf(), false))
        taskInfoList[0].childList.add(ChildInfo("1.2 do math", 2))
        taskInfoList.add(TaskInfo("2.play games", 1, mutableListOf(), false))

        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerViewAdapter = TaskRecyclerViewAdapter(this)
        recyclerview.adapter = recyclerViewAdapter
        recyclerViewAdapter.submitList(taskInfoList.toList())
    }

    override fun onItemClick(position: Int) {
        if(taskInfoList[position].isExpand) {
            taskInfoList[position].isExpand = false
            folder(position)
        } else {
            taskInfoList[position].isExpand = true
            expand(position)
        }
    }

    fun expand(position: Int) {
        var nowPosition = position
        for(childInfo in taskInfoList[position].childList) {
            taskInfoList.add(nowPosition + 1, TaskInfo(childInfo.content, childInfo.TYPE, mutableListOf(), false))
            nowPosition++
        }
        recyclerViewAdapter.submitList(taskInfoList.toList())
    }

    fun folder(position: Int) {
        for(childInfo in taskInfoList[position].childList) {
            taskInfoList.removeAt(position + 1)
        }
        recyclerViewAdapter.submitList(taskInfoList.toList())
    }
}