package com.generlas.retrofittest

import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


/**
 * description ： TODO:类的作用
 * date : 2025/3/20 11:35
 */
object AppNetWork {
    private val appService = ServiceCreator.create<AppService>()

    suspend fun getData(user: String, token: String) = appService.getData(user, token).await()

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                   val body = response.body()
                    if(body != null) {
                        continuation.resume(body)
                    } else {
                        continuation.resumeWithException(RuntimeException("response body is null"))
                    }
                }

            })
        }
    }

    //fun getData(user: String, token: String) = liveData(Dispatchers.IO)
}