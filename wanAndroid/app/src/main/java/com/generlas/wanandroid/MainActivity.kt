package com.generlas.wanandroid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var mBtnButton1:Button = findViewById(R.id.btn_main_button1)
        mBtnButton1.setOnClickListener {
            val intent = Intent(this,MainActivity2::class.java)
            intent.putExtra("extra_data","successful")
            startActivity(intent)
            Toast.makeText(this,"is entered",Toast.LENGTH_SHORT).show()
        }
    }

}

