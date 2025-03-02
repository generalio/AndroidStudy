package com.generlas.jetpacktest

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.generlas.jetpacktest.databinding.ActivityMainBinding
import kotlin.concurrent.thread

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

        Room()
    }

    // Room的使用
    fun Room() {
        val userDao = AppDatabase.getDatabase(this).UserDao()
        val user1 = User("Tom", "Brady", 40)
        val user2 = User("Tom", "Hanks", 63)
        thread {
            user1.id = userDao.insertUser(user1)
            user2.id = userDao.insertUser(user2)
        }
        thread {
            user1.age = 42
            userDao.updateUser(user1)
        }
        thread {
            userDao.deleteUserByLastName("Hanks")
        }
        thread {
            for(user in userDao.loadAllUsers()) {
                Log.d("zzx",user.toString());
            }
        }
    }
}