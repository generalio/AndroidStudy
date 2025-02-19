package com.generlas.wanandroid

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class MainActivity4 : AppCompatActivity() {
    lateinit var mBtnSend:Button
    lateinit var mTvSend:TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
        mBtnSend = findViewById(R.id.btn_send)
        mTvSend = findViewById(R.id.tv_sendText)
        mBtnSend.setOnClickListener {
            sendRequestWithOkHttp()
        }
        /**POST
         val requestBody = FormBody.Builder()
                .add("username","admin")
                .add("password","123456").build()
         val request = Request.Builder().url("https://www.baidu.com").post(requestBody).build()
         */
    }
    private fun sendRequestWithOkHttp() {
        thread {
            try {
                Log.d("zzx","(111:)-->>");
                val client = OkHttpClient()
                Log.d("zzx","(create:)-->>");
                val request = Request.Builder().url("https://www.wanandroid.com/banner/json").build()
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                if(responseData != null) {
                    parseJSONWithJSONObject(responseData)
                }
            } catch (e:Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun parseJSONWithJSONObject(response: String) {
        val jsonData = JSONObject(response)
        Log.d("zzx","(12345687:)-->>");
        val jsonArray = jsonData.getJSONArray("data")
        Log.d("zzx","(11211311131231:)-->>");
        val listData = ArrayList<String>()
        for(i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val desc = jsonObject.getString("desc")
            listData.add(desc)
        }
        showResponse(listData.get(0))
    }

    private fun showResponse(response:String) {
        runOnUiThread {
            mTvSend.setText(response)
        }
    }
}

/*private fun sendRequestWithHttpURLConnection() {
        thread {
            var connection:HttpURLConnection?=null
            try {
                val response = StringBuilder()
                val url = URL("https://www.baidu.com")
                connection = url.openConnection() as HttpURLConnection
                val input = connection.inputStream
                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }
                showResponse(response.toString())
            } catch (e:Exception) {
                e.printStackTrace()
            } finally {
                connection?.disconnect()
            }
        }
    }*/