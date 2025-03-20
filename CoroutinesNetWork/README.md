## Retrofit+协程的使用学习

协程能模拟在单线程上使用多线程进行任务，能使异步代码看着像同步代码，我们可以通过这些来很方便的进行网络请求。

导入依赖:

```kotlin
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("androidx.activity:activity-ktx:1.10.1")
```

创建API接口:

```kotlin
interface API {
    @GET("banner/json")
    suspend fun getBanner(): ResponseResult<List<Banner>>
}
```

Retrofit现在原生就支持了协程，只需要声明成`suspend`关键字就可以了，返回值也可以直接设置成`ResponseResult`数据类，而不是Call回调。

创建Retrofit构建器:

```kotlin
object AppService {

    private const val BASE_URL = "https://www.wanandroid.com/"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    inline fun <reified T> create(): T = retrofit.create(T::class.java)

}
```

然后定义网络仓库层:

```kotlin
object BannerNetWork {
    val appService = AppService.create<API>()

    suspend fun getBanner() = appService.getBanner()
}
```

注意这里也要声明成挂起函数。

然后`ViewModel`里面:

```kotlin
class BannerViewModel: ViewModel() {
    val bannerViewModel: LiveData<List<Banner>> get() = _bannerLiveData
    private val _bannerLiveData = MutableLiveData<List<Banner>>()

    fun getBanner() {
        viewModelScope.launch {
            val res = try {
                BannerNetWork.getBanner()
            }catch (e: Exception) {
                null
            }
            if(res != null && res.errorCode == 0 && res.data != null) {
                _bannerLiveData.postValue(res.data)
            }
        }
    }
}
```

这里的`viewModelScope`来自于`androidx.lifecycle:lifecycle-viewmodel-ktx`，为`ViewModel`提供了一个`CoroutineScope`，会在`ViewModel`清除时自动取消，否则就需要用`CoroutineScope()`，然后手动创建`Job()`并管理生命周期。

这里`launch`启动后，发起了网络请求会自动调用IO线程，然后在请求完成后返回数据后又会自动切换到主线程上，我们只需要设置`liveData`就好了。

然后`MainActivity`中:

```kotlin
class MainActivity : AppCompatActivity() {
    
    private val viewModel: BannerViewModel by viewModels() //使用的activit-ktx能快速设置ViewModel()
    
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
```

这样就能使用协程很方便的完成网络请求了。