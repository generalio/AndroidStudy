package com.generals.servicestudy

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var startButton: Button
    lateinit var stopButton: Button
    lateinit var bindButton: Button
    lateinit var unbindButton: Button

    lateinit var downloadBinder: MyService.DownLoadBinder

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            downloadBinder = service as MyService.DownLoadBinder
            downloadBinder.startDownload()
            downloadBinder.getProgress()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
        }

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton = findViewById(R.id.btn_start)
        stopButton = findViewById(R.id.btn_stop)
        bindButton = findViewById(R.id.btn_bind)
        unbindButton = findViewById(R.id.btn_unbind)

        startButton.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            startService(intent)
        }

        stopButton.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            stopService(intent)
        }

        bindButton.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }

        unbindButton.setOnClickListener {
            unbindService(connection)
        }
    }
}