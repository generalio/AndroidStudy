package com.generlas.retrofittest

import io.reactivex.rxjava3.core.Observable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * description ： TODO:类的作用
 * date : 2025/2/19 17:20
 */
interface AppService {

    @GET("banner/json")
    fun getAppData(): Observable<banner>

    @GET("article/list/{page}/json")
    fun getPage(@Path("page") page: Int): Observable<PassageResponse>

    @GET("get_data.json")
    fun getData(@Query("u") user: String, @Query("t") token: String): Call<Passage>

    @POST("data/create")
    fun createData(@Body data: Data): Call<ResponseBody>

    @GET("get_data.json")
    fun getHeaderData(@Header("User-Agent") userAgent: String, @Header("Cache-Control") cacheControl: String): Call<Data>
}