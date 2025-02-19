package com.generlas.jetpacktest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * description ： TODO:viewBinding的封装
 * date : 2025/2/18 22:34
 */
abstract class BaseActivity<T: ViewBinding>: AppCompatActivity() {
    val binding get() = _binding!!
    var _binding: T? = null

    abstract fun inflateBinding(): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = inflateBinding()
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}