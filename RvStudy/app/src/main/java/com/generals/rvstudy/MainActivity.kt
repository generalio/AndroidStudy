package com.generals.rvstudy

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var rv : RecyclerView
    lateinit var rvAdapter: RvAdapter
    val rvList: List<String> = listOf("abcde","asdasdsa","123adq","qwdq22","qwd12e12")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv = findViewById(R.id.rv)
        rv.layoutManager = LinearLayoutManager(this)
        rvAdapter = RvAdapter(rvList)
        rv.adapter = rvAdapter

    }
}