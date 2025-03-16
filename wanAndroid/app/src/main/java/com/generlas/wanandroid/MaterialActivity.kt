package com.generlas.wanandroid

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.google.android.material.appbar.CollapsingToolbarLayout

class MaterialActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val collapsingToolbar: CollapsingToolbarLayout = findViewById(R.id.collapsingToolbar)
        collapsingToolbar.title = "椎名日和"
        val imageView: ImageView = findViewById(R.id.fruitImageView)
        Glide.with(this).load(R.drawable.rihe).into(imageView)
        var content = "rihe"
        for(i in 0 .. 500) {
            content += "rihe"
        }
        val contextView: TextView = findViewById(R.id.fruitContentText)
        contextView.text = content
    }
}