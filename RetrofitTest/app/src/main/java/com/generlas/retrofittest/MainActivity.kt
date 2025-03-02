package com.generlas.retrofittest

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.generlas.retrofittest.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    private val compositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {

            val appService = ServiceCreator.create<AppService>()

            val disposable = appService.getPage(0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ passageResponse ->
                    Toast.makeText(this, passageResponse.data.datas[0].title,Toast.LENGTH_SHORT).show()
                },{ error ->
                    Toast.makeText(this,error.message,Toast.LENGTH_SHORT).show()
                })
            compositeDisposable.add(disposable)

            val disposable2  = appService.getAppData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({response ->
                    binding.textView.text = response.data[0].title
                },{ error ->
                    Log.d("zzx","(${error.message}:)-->>");
                })

            compositeDisposable.add(disposable2)
            /*appService.getAppData().enqueue(object  : Callback<banner> {

                override fun onFailure(call: Call<banner>, t: Throwable) {
                    Log.d("zzx",t.message.toString());
                }

                override fun onResponse(call: Call<banner>, response: Response<banner>) {
                    val list = response.body()
                    if(list != null) {
                        binding.textView.text = list.data[0].title
                    }
                }

            })*/
        }

        /*
        val appService = ServiceCreator.create(AppService::class.java)
        appService.
        */

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}