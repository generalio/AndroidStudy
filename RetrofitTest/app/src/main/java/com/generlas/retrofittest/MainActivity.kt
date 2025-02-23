package com.generlas.retrofittest

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.generlas.retrofittest.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val appService = retrofit.create(AppService::class.java)
            appService.getAppData().enqueue(object  : Callback<banner> {

                override fun onFailure(call: Call<banner>, t: Throwable) {
                    Log.d("zzx",t.message.toString());
                }

                override fun onResponse(call: Call<banner>, response: Response<banner>) {
                    val list = response.body()
                    if(list != null) {
                        binding.textView.text = list.data[0].title
                    }
                }

            })
        }

        /*
        val appService = ServiceCreator.create(AppService::class.java)
        appService.
        */

    }
}