package com.generlas.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * description ： TODO:类的作用
 * date : 2025/3/8 16:00
 */
class CustomView @JvmOverloads constructor(context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0) : View(context, attrs, defStyleAttr, defStyleRes) {

    private val mBlackPaint = Paint().apply {
        color = Color.BLACK
    }

    private var mCircleRadius = 100
    private var mCircleGravity = 0
    init {
        if(attrs != null) {
            val ty = context.obtainStyledAttributes(attrs, R.styleable.CustomView)
            mCircleRadius = ty.getDimensionPixelSize(
                R.styleable.CustomView_customView_circleRadius,
                mCircleRadius
            )

            mCircleGravity = ty.getInt(
                R.styleable.CustomView_customView_circleGravity,
                mCircleGravity
            )
            ty.recycle()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(0f,0f,100f,200f,mBlackPaint)
        canvas.drawCircle((width * mCircleGravity / 2).toFloat(), (height * mCircleGravity / 2).toFloat(), mCircleRadius.toFloat(), mBlackPaint)
    }


}