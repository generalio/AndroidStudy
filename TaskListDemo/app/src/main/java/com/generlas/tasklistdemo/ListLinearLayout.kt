package com.generlas.tasklistdemo

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewConfiguration
import android.widget.LinearLayout
import kotlin.math.abs

/**
 * description ： TODO:类的作用
 * date : 2025/3/9 14:49
 */
class ListLinearLayout(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private val slopTouch = ViewConfiguration.get(context).scaledTouchSlop

    private var lastX = 0
    private var lastY = 0
    var initialX = 0

    //需在这个方法里面设置初始位置
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        when(ev.action) {
            MotionEvent.ACTION_DOWN -> {
                initialX = ev.x.toInt() - getChildAt(1).translationX.toInt()
                lastX = ev.x.toInt()
                lastY = ev.y.toInt()
            }

            MotionEvent.ACTION_MOVE -> {
                if(abs(ev.x - lastX) > slopTouch || abs(ev.y - lastY) > slopTouch) {
                    return true
                }
                lastX = ev.x.toInt()
                lastY = ev.y.toInt()
            }
        }
        return super.onInterceptTouchEvent(ev)
    }


    //这里面down事件不会触发
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val parentDelete = getChildAt(1)
        val textLayout = getChildAt(0)
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                initialX = event.x.toInt() - getChildAt(1).translationX.toInt()
            }

            MotionEvent.ACTION_MOVE -> {
                val transitionX = (event.x - initialX).coerceIn(-parentDelete.width.toFloat(),0F)
                textLayout.translationX = transitionX
                parentDelete.translationX = transitionX
            }

            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                if(-textLayout.translationX> parentDelete.width / 2) {
                    textLayout.animate().translationX(-parentDelete.width.toFloat())
                    parentDelete.animate().translationX(-parentDelete.width.toFloat())
                } else {
                    textLayout.animate().translationX(0F)
                    parentDelete.animate().translationX(0F)
                }
            }
        }

        return true
    }

}