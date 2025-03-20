package com.generlas.coroutinesnetwork

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: BannerViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getBanner()

        viewModel.bannerViewModel.observe(this){banners ->
            if(banners != null) {
                Log.d("zzx","(${banners[0].title}:)-->>");
            }
        }
    }
}