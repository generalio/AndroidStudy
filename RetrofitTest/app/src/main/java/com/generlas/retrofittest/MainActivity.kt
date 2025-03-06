package com.generlas.retrofittest

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.Consumer
import com.generlas.retrofittest.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private val compositeDisposable = CompositeDisposable()
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {

            val appService = ServiceCreator.create<AppService>()
            rxjava()
            operation()
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

    @SuppressLint("CheckResult")
    fun rxjava() {
        /*
        Observable
            .just("this is create Demo")
            .subscribe(
                { Log.d("zzx","(msg:$it)-->>")},
                { Log.d("zzx","(error:${it.message?: "Unknown Error"})-->>")},
                { Log.d("zzx","(onComplete:)-->>")},
            )*/
        Observable.just("this is lifeCycleExample")
            .doOnSubscribe {
                //发生订阅后
                Log.d("zzx","(doOnSubscribe:)-->>");
            }.doOnLifecycle(
                //订阅后是否可以取消
                { Log.d("zzx","(doOnLifecycle is disposed${it.isDisposed}:)-->>");},
                { Log.d("zzx","(doOnLifecycle is run:)-->>");}
            ).doOnEach {
                //每次发送数据都会执行
                Log.d("zzx","doOnEach:${when {
                    it.isOnNext -> "onNext"
                    it.isOnError -> "onError"
                    it.isOnComplete -> "onComplete"
                    else -> "nothing"
                }}");
            }.doOnNext {
                //onNext前调用
                Log.d("zzx","(doOnNext:$it)-->>");
            }.doAfterNext {
                //onNext后调用
                Log.d("zzx","(doAfterNext:$it)-->>");
            }.doOnError {
                //onError前调用
                Log.d("zzx","(doOnError:${it.message ?: "unknown error message"}:)-->>");
            }.doOnComplete {
                //正常调用onComplete调用
                Log.d("zzx","(doOnComplete:)-->>");
            }.doAfterTerminate {
                //onComplete或onError执行后触发
                Log.d("zzx","(doAfterTerminate:)-->>");
            }.doFinally {
                //中止后调用，无论正常执行或异常终止
                Log.d("zzx","(doFinally:)-->>");
            }.subscribe{ Log.d("zzx","(onNext value:$it)-->>");}
    }

    @SuppressLint("CheckResult")
    fun operation() {
        //map
        Observable.just(1,2,3)
            .map {
                it.toString()
            }.subscribe(object : Observer<String> {
                override fun onSubscribe(d: Disposable) {
                    Log.d("zzx","(${Thread.currentThread().name}:)-->>");
                }

                override fun onError(e: Throwable) {

                }

                override fun onNext(t: String) {
                    Log.d("zzx","(int to str:$t)-->>");
                }

                override fun onComplete() {

                }

            })

        //flatmap
        Observable.create<Int> {
            it.onNext(1)
        }
            .flatMap {
                Observable.create<Int> { emitter ->
                    emitter.onNext(it + 2)
                }
            }
            .subscribe {
                Log.d("zzx","($it:)-->>");
            }
        //ConcactMap
        Observable.create<Int>{
            it.onNext(1)
            it.onNext(2)
            it.onNext(3)
            it.onNext(4)
            it.onNext(5)
            it.onNext(6)
        }
            .concatMap {
                Observable.create<Int> {emitter ->
                    emitter.onNext(it)
                    emitter.onComplete()
                }
                    .subscribeOn(Schedulers.io())
            }
            .subscribe{
                Log.d("zzx",it.toString() + " " + Thread.currentThread());
            }

        //concatmap
        Observable.create<Int>{
            it.onNext(1)
            it.onNext(2)
            it.onNext(3)
            it.onNext(4)
            it.onNext(5)
            it.onNext(6)
        }
            .concatMap {
                Observable.create<Int> {emitter ->
                    emitter.onNext(it)
                    emitter.onComplete()
                }
                    .subscribeOn(Schedulers.io())
            }
            .subscribe{
                println(it.toString() + " " + Thread.currentThread())
            }


        //merge
        val odds = Observable.just(1,3,5,7)
        val events = Observable.just(2,4,6,8)
        Observable.merge(odds,events).subscribe{}

        //repeat
        Observable.just(1,2,3)
            .repeat(3)

        //filter
        Observable.just(1,2,3)
            .filter {
                it >= 2
            }
    }

    fun login(): Observable<String> {
        Thread.sleep(1000)
        val cookie:String = "xxxxxx"
        return Observable.just(cookie)
    }

    fun requireInfo(cookie: String):Observable<String> {
        Thread.sleep(1000)
        val info = "xxx"
        return Observable.just(info)
    }


    @SuppressLint("CheckResult")
    fun request() {
        login()
            .flatMap {
                requireInfo(it)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { info ->
                binding.textView.text = info //更新UI,因为已经在主线程中
            }
    }
}