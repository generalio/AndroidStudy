package com.generlas.wanandroid

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val mTvTextview2:TextView = findViewById(R.id.btn_main2_textView2)
        val mBtnButton:Button = findViewById(R.id.btn_main2_button)
        val extraData = intent.getStringExtra("extra_data")
        //Log.d("zzx0826","extra data is $extraData")
        mTvTextview2.setText(extraData)
        mBtnButton.setOnClickListener {
            AlertDialog.Builder(this).apply {
                setTitle("This is Dialog")
                setMessage("Something important")
                setCancelable(false)
                setPositiveButton("OK") {dialog,which -> mTvTextview2.setText("approve")}
                setNegativeButton("Cancel"){dialog,which -> mTvTextview2.setText("No!")}
                show()
            }
        }
    }
}