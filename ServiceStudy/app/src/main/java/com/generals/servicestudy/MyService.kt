package com.generals.servicestudy

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

class MyService : Service() {

    private val mBinder = DownLoadBinder()

    class DownLoadBinder : Binder() {

        fun startDownload() {
            Log.d("zzx", "startDownload executed")
        }

        fun getProgress(): Int {
            Log.d("zzx", "getProgress executed")
            return 0
        }

    }

    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }

    @SuppressLint("ForegroundServiceType")
    override fun onCreate() {
        super.onCreate()
        Log.d("zzx", "onCreate executed ")
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel("my_service", "前台Service通知",NotificationManager.IMPORTANCE_DEFAULT)
        manager.createNotificationChannel(channel)
        val intent = Intent(this, MainActivity::class.java)
        val pi = PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_IMMUTABLE)
        val notification = NotificationCompat.Builder(this, "my_service")
            .setContentTitle("This is content title")
            .setContentText("TThis is content text")
            .setSmallIcon(R.drawable.avatar)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.avatar))
            .setContentIntent(pi)
            .build()
        startForeground(1,notification)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}