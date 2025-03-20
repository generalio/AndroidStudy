package com.generlas.wanandroid

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * description ： 协程的学习
 * date : 2025/3/18 11:07
 */
fun main() = runBlocking{
    /*
    launch的用法
    val job = GlobalScope.launch {
        delay(1000)
        println("Hello Coroutines!")
    }

    Thread.sleep(2000)
    */

    /*
    async具有返回值
    start = CoroutineStart.LAZY类似于懒加载，只有在job或deferred被显示调用时才会启动协程
    如果在GlobalScope.launch处使用start = CoroutineStart.LAZY则不会生效，因为没有被显示调用过。

    GlobalScope.launch {
        val result1 = async {
            delay(2000)
            1
        }
        val result2 = async(start = CoroutineStart.LAZY) {
            delay(1000)
            2
        }
        val result = result1.await() + result2.await()
        println(result)
    }

    Thread.sleep(4000)
    */

    /*
    通过cancelAndJoin()来取消任务，使得result.await()获取不到结果

    GlobalScope.launch {
        val result = async {
            delay(2000)
            1
        }
        result.invokeOnCompletion {
            if(it != null) {
                println("exception: ${it.message}")
            } else {
                println("result is complete")
            }
        }

        result.cancelAndJoin()
        println(result.await())
    }

    Thread.sleep(5000)
     */

    /*
    fun main() = runBlocking {
        launch {
            delay(1000)
            println("Hello world!")
        }
        delay(2000)
    }
    runBlocking 创建的协程直接运行在当前线程上，阻塞当前线程直到结束。
    runBlocking内部可以创建其他协程，但是其他协程内部不能开启runBlocking
     */

    /*
    delay挂起函数，类似sleep(),但delay()不会阻塞线程

    println("1:current thread is ${Thread.currentThread().name}")

    GlobalScope.launch {
        println("3:current thread is ${Thread.currentThread().name}")
        delay(1000L)
        println("4:current thread is ${Thread.currentThread().name}")
    }

    println("2:current thread is ${Thread.currentThread().name}")
    Thread.sleep(2000L)
    println("5:current thread is ${Thread.currentThread().name}")

     */

    /*
    withContext不会创建新的协程，类似于runBlocking。最后一行为返回值，且context参数必须传值，Dispatchers.Main为android主线程
    withContext使用NonCancellable时，即使被调用者取消了，也可以继续执行完。

    GlobalScope.launch {
        val result1 = withContext(Dispatchers.Default) {
            delay(2000)
            1
        }

        val result2 = withContext(Dispatchers.IO) {
            delay(1000)
            2
        }
        0

        val result = result1 + result2
        println(result)
    }

    Thread.sleep(5000)
     */

    /*
    Channel机制：一个作为生产者发送消息，另一个作为消费者消费信息，通过channel建立关系

    val channel = Channel<Int>()
    launch(Dispatchers.Default) {
        repeat(5) {
            i ->
            delay(200)
            channel.send((i + 1) * (i + 1))
            if(i == 2) {
                /*
                Channel可以关闭，但是后面消费者想要接受就只能捕获异常了。
                channel.close()
                 */
            }
        }
    }

    launch(Dispatchers.Default) {
        repeat(5) {
            println(channel.receive())
        }
    }
    delay(2000)
    println("Receive Done!")
     */

    /*
    与runblocking相似，coroutineScope也会将外部协程挂起知道当前的协程全部执行结束
    coroutineScope {
        launch {
            for(i in 1..10) {
                println(i)
                delay(1000)
            }
        }
    }
    println("coroutineScope finished")
     */

    /*

    val job = Job()
    val scope = CoroutineScope(job)
    scope.launch {
        //处理逻辑
    }
    job.cancel()
     */

    /*
    使用await()方法获取运算结果，同时await()会将当前协程阻塞住直到可以获得async函数的执行结果
    val result = async {
        5 + 5
    }.await()
    println(result)
     */

    /*
    await()在当前async代码块完全结束前一直阻塞线程，故运行效率很低
    我们可以在需要结果时才调用await()这样两个任务就能并行了，大大提高了效率
    val start = System.currentTimeMillis()
    val deferred1 = async {
        delay(1000)
        5 + 5
    }
    val deferred2 = async {
        delay(1000)
        4 + 6
    }
    println("result is ${deferred1.await() + deferred2.await()}.")
    val end = System.currentTimeMillis()
    println("cost ${end - start} milliseconds")
     */
}

/*
此时无法提供协程作用域，无法创建launch
suspend fun printDot() {
    println(".")
    delay(1000)
}
 */

/*
suspend fun printDot() = coroutineScope {
    launch {
        println(".")
        delay(1000)
    }
}
使用coroutineScope后就可以创建一个协程作用域了
 */