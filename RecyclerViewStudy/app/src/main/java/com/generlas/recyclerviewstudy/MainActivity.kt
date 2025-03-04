package com.generlas.recyclerviewstudy

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Collections

class MainActivity : AppCompatActivity() {

    lateinit var recyclerview: RecyclerView
    lateinit var recyclerViewAdapter: RecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val list: MutableList<ListInfo> = mutableListOf(ListInfo("1", "a"), ListInfo("2", "b"), ListInfo("3", "c"), ListInfo("4", "d"), ListInfo("5", "e"),ListInfo("1", "a"), ListInfo("2", "b"), ListInfo("3", "c"), ListInfo("4", "d"), ListInfo("5", "e"),ListInfo("1", "a"), ListInfo("2", "b"), ListInfo("3", "c"), ListInfo("4", "d"), ListInfo("5", "e"))
        recyclerview = findViewById(R.id.recyclerView)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerViewAdapter = RecyclerViewAdapter(list)
        recyclerview.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))
        recyclerview.adapter = recyclerViewAdapter

        val helper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                val swipeFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                return makeMovementFlags(dragFlags, swipeFlags)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val from = viewHolder.adapterPosition
                val to = target.adapterPosition
                Collections.swap(list, from, to)
                recyclerViewAdapter.notifyItemMoved(from, to)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                list.removeAt(position)
                recyclerViewAdapter.notifyItemRemoved(position)
            }

        })
        helper.attachToRecyclerView(recyclerview)

    }
}