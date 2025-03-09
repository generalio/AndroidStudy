package com.generlas.tasklistdemo

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_DRAG
import androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_IDLE
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Collections

class MainActivity : AppCompatActivity(), TaskRecyclerViewAdapter.OnItemClickListener {

    lateinit var recyclerview: RecyclerView
    lateinit var recyclerViewAdapter: TaskRecyclerViewAdapter
    lateinit var mBtnAddParent: Button
    private val taskInfoList: MutableList<TaskInfo> = mutableListOf()

    @SuppressLint("InflateParams", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview = findViewById(R.id.rv)
        mBtnAddParent = findViewById(R.id.btn_main_addParent)

        taskInfoList.add(TaskInfo("1.点下方按钮添加任务", 1, mutableListOf(), false))
        taskInfoList.add(TaskInfo("2.单击任务可添加子任务", 1, mutableListOf(), false))
        taskInfoList.add(TaskInfo("3.侧滑删除任务", 1, mutableListOf(), false))

        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerViewAdapter = TaskRecyclerViewAdapter(this)
        //添加边框的线
        recyclerview.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))

        recyclerview.adapter = recyclerViewAdapter
        recyclerViewAdapter.submitList(taskInfoList.toList())

        val helper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                return makeFlag(ACTION_STATE_DRAG,dragFlags) or makeFlag(ACTION_STATE_IDLE, dragFlags)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val from = viewHolder.adapterPosition
                val to = target.adapterPosition

                Collections.swap(taskInfoList, from, to)
                recyclerViewAdapter.submitList(taskInfoList.toList())

                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            }

        })
        helper.attachToRecyclerView(recyclerview)

        mBtnAddParent.setOnClickListener {
            showAddParentDialog()
        }
    }

    //展示添加任务的窗口
    fun showAddParentDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_change, null)
        val dialogText: EditText = dialogView.findViewById(R.id.et_change_content)
        val dialogBuilder = AlertDialog.Builder(this)
            .setTitle("添加任务")
            .setView(dialogView)
            .setPositiveButton("确认") {dialog, which -> addParentTask(dialogText.text.toString()) }
            .setNegativeButton("取消") {dialog, which -> }
        dialogBuilder.show()
    }

    //添加一级任务
    fun addParentTask(title: String) {
        taskInfoList.add(TaskInfo(title, 1, mutableListOf(), false))
        recyclerViewAdapter.submitList(taskInfoList.toList())
    }

    //添加二级任务
    fun addChildTask(position: Int, content: String) {
        if(taskInfoList[position].isExpand) {
            taskInfoList[position].childList.add(ChildInfo(content, 2))
            val newPosition = position + taskInfoList[position].childList.size
            taskInfoList.add(newPosition, TaskInfo(content, 2, mutableListOf(), false))
            recyclerViewAdapter.submitList(taskInfoList.toList())
        } else {
            taskInfoList[position].childList.add(ChildInfo(content, 2))
            recyclerViewAdapter.submitList(taskInfoList.toList())
        }
    }

    //展开按钮点击后状态的改变
    override fun onExpandClick(position: Int) {
        if(taskInfoList[position].isExpand) {
            taskInfoList[position].isExpand = false
            folder(position)
        } else {
            taskInfoList[position].isExpand = true
            expand(position)
        }
    }

    //点击事件回调
    override fun onItemClick(position: Int) {
        showAddChildDialog(position)
    }

    override fun onDeleteParentClick(position: Int) {
        folder(position)
        taskInfoList.removeAt(position)
        recyclerViewAdapter.submitList(taskInfoList.toList())
    }

    override fun onDeleteChildClick(position: Int) {
        var nowPosition = position
        var childPosition = 0

        while (nowPosition < taskInfoList.size && nowPosition > 0 && taskInfoList[nowPosition].TYPE == 2) {
            nowPosition--
            childPosition++
        }

        taskInfoList.removeAt(position)
        taskInfoList[nowPosition].childList.removeAt(childPosition - 1)

        recyclerViewAdapter.submitList(taskInfoList.toList())
    }

    fun showAddChildDialog(position: Int) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_change, null)
        val dialogText: EditText = dialogView.findViewById(R.id.et_change_content)
        val dialogBuilder = AlertDialog.Builder(this)
            .setTitle("添加子任务")
            .setView(dialogView)
            .setPositiveButton("确认") {dialog, which -> addChildTask(position, dialogText.text.toString()) }
            .setNegativeButton("取消") {dialog, which -> }
        dialogBuilder.show()
    }

    //展开子任务
    fun expand(position: Int) {
        var nowPosition = position
        for(childInfo in taskInfoList[position].childList) {
            taskInfoList.add(nowPosition + 1, TaskInfo(childInfo.content, childInfo.TYPE, mutableListOf(), false))
            nowPosition++
        }
        recyclerViewAdapter.submitList(taskInfoList.toList())
    }

    //折叠子任务
    fun folder(position: Int) {
        for(childInfo in taskInfoList[position].childList) {
            if(position + 1 < taskInfoList.size) {
                taskInfoList.removeAt(position + 1)
            }

        }
        recyclerViewAdapter.submitList(taskInfoList.toList())
    }
}