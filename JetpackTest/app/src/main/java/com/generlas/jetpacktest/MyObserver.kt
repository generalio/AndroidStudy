package com.generlas.jetpacktest

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

/**
 * description ： TODO:类的作用
 * date : 2025/2/12 18:12
 */
class MyObserver : DefaultLifecycleObserver{
    override fun onCreate(owner: LifecycleOwner) {
        Log.d("zzx","(OnCreate:)-->>");
    }

    override fun onStart(owner: LifecycleOwner) {
        Log.d("zzx","(OnStart:)-->>");
    }

    override fun onResume(owner: LifecycleOwner) {
        Log.d("zzx","(OnResume:)-->>");
    }

    override fun onPause(owner: LifecycleOwner) {
        Log.d("zzx","(OnPause:)-->>");
    }

    override fun onStop(owner: LifecycleOwner) {
        Log.d("zzx","(onStop:)-->>");
    }

    override fun onDestroy(owner: LifecycleOwner) {
        Log.d("zzx","(onDestroy:)-->>");
    }
}