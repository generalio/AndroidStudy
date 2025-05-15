package com.generlas.customview

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AnimateActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animate)

        val btn : Button = findViewById(R.id.btn)
        val text: TextView = findViewById(R.id.tv)
        val mBtnStop : Button = findViewById(R.id.btn_stop)

        val scaleAnimation = ScaleAnimation(0.0F,1.4F,0.0F,1.4F,Animation.RELATIVE_TO_SELF,0.5F,Animation.RELATIVE_TO_SELF,0.5F) //缩放动画
        scaleAnimation.duration = 1000

        val alphaAnimation = AlphaAnimation(1.0F,0.1F) //透明度动画
        alphaAnimation.duration = 3000
        alphaAnimation.fillBefore = true

        val rotateAnimation = RotateAnimation(0F,360F,Animation.RELATIVE_TO_SELF,0.5F,Animation.RELATIVE_TO_SELF,0.5F) //旋转动画
        rotateAnimation.duration = 2000
        rotateAnimation.repeatCount = Animation.INFINITE
        rotateAnimation.fillAfter = true

        val translateAnimation = TranslateAnimation(Animation.ABSOLUTE,0F,Animation.ABSOLUTE,100F,Animation.ABSOLUTE,0F,Animation.ABSOLUTE,100F) //移动动画
        translateAnimation.duration = 2000
        translateAnimation.fillBefore = true

        val setAnimation = AnimationSet(true) //true -> 共用一个插值器 false -> 各自用各自的插值器
        setAnimation.addAnimation(scaleAnimation)
        setAnimation.addAnimation(alphaAnimation)
        setAnimation.addAnimation(rotateAnimation)


        btn.setOnClickListener {
//            val animation = AnimationUtils.loadAnimation(this, R.anim.translateanim) //通过xml的方式构建动画
            /**
             * pivotXYType的取值有三个:
             * Animation.ABSOLUTE -> 传入的是具体数值（相当于XY轴)
             * Animation.RELATIVE_TO_SELF -> 传入的百分数相对自身比例
             * Animation.RELATIVE_TO_PARENT -> 传入的百分数相当于父容器的比例
             */

            text.startAnimation(setAnimation)
        }

        /** 动画的一些其他方法
         * cancel() 取消动画
         * reset() 重置动画
         * setAnimationListener(Animation.AnimationListener listener) //动画监听
         *      onAnimationEnd(Animation animation)
         *      onAnimationRepeat(Animation animation)
         *      onAnimationStart(Animation animation)
         */
        mBtnStop.setOnClickListener {
            scaleAnimation.cancel() //取消动画
            alphaAnimation.cancel()
            rotateAnimation.cancel()
        }

        //监听器
        scaleAnimation.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                Log.d("zzx", "start")
            }

            override fun onAnimationEnd(p0: Animation?) {
                Log.d("zzx", "stop")
            }

            override fun onAnimationRepeat(p0: Animation?) {
                Log.d("zzx", "repeat")
            }

        })
    }
}