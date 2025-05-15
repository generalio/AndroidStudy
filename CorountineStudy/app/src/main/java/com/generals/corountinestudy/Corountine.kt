package com.generals.corountinestudy

import android.provider.Settings.Global
import android.util.Log
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

/**
 * @Desc : 协程学习
 * @Author : zzx
 * @Date : 2025/5/12 23:30
 */

@OptIn(DelicateCoroutinesApi::class)
fun main() {
//    GlobalScope.launch(context = Dispatchers.IO) {
//        delay(1000)
//        log("launch")
//    }
//    Thread.sleep(2000)
//    log("end")

//    runBlocking {
//        launch {
//            repeat(3) {
//                delay(100)
//                log("launchA - $it")
//            }
//        }
//        launch {
//            repeat(3) {
//                delay(100)
//                log("launchB - $it")
//            }
//        }
//        GlobalScope.launch {
//            repeat(3) {
//                delay(120)
//                log("GlobalScope - $it")
//            }
//        }
//    }
//    log("end")

//    runBlocking {
//        launch {
//            delay(100)
//            log("Task from runBlocking")
//        }
//        coroutineScope {
//            launch {
//                delay(500)
//                log("Task from nested launch")
//            }
//            delay(50)
//            log("Task from coroutine scope")
//        }
//        log("Coroutine scope is over")
//    }

    runBlocking {
//        flow {
//            for(i in 1..5) {
//                delay(100)
//                emit(i)
//            }
//        }.collect {
//            println(it)
//        }
//        flowOf(1,2,3,4,5) //== listOf(1,2,3,4,5).asFlow()
//            .onEach {
//                delay(100)
//            }
//            .collect {
//                println(it)
//            }
//        val time = measureTimeMillis {
//            flow {
//                for (i in 1..5) {
//                    delay(100)
//                    emit(i)
//                }
//            }.collect {
//                delay(100)
//                println(it)
//            }
//        }
//        print("cost $time")

//        val time = measureTimeMillis {
//            channelFlow {
//                for (i in 1..5) {
//                    delay(100)
//                    send(i)
//                }
//            }.collect {
//                delay(100)
//                println(it)
//            }
//        }
//        print("cost $time")

        val time = measureTimeMillis {
            flow {
                for (i in 1..5) {
                    delay(100)
                    emit(i)
                }
            }.flowOn(Dispatchers.IO)
                .collect {
                    delay(100)
                    println(it)
                }
        }
        print("cost $time")
    }
}

private fun log(msg: Any?)


= println("[${Thread.currentThread().name}] $msg")