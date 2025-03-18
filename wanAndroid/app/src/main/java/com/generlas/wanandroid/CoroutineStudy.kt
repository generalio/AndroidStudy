package com.generlas.wanandroid

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * description ： 协程的学习
 * date : 2025/3/18 11:07
 */
fun main() {
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


}