package com.generlas.jetpacktest

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.generlas.jetpacktest.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    lateinit var viewModel: MainViewModel
    lateinit var mBtnPlus: Button
    lateinit var mTvText: TextView
    lateinit var mBtnClear: Button

    override fun inflateBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(MyObserver())

        viewModel = ViewModelProvider(this, MainViewModelFactory(100)).get(MainViewModel::class.java)

        mTvText = findViewById(R.id.tv_main_info)
        mBtnPlus = findViewById(R.id.btn_main_plus)
        mBtnClear = findViewById(R.id.btn_main_clear)

        binding.btnMainPlus.setOnClickListener {
            viewModel.plusOne()
        }
        binding.btnMainClear.setOnClickListener {
            viewModel.clear()
        }
        viewModel.counter.observe(this) {count ->
            mTvText.text = count.toString()
        }
    }
}