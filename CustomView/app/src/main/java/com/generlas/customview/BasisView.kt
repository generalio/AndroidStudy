package com.generlas.customview

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.Region
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator

/**
 * @Desc : 自定义View
 * @Author : zzx
 * @Date : 2025/4/28 23:13
 */

@SuppressLint("Recycle")
class BasisView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val dst = Path()
    val path = Path()
    lateinit var pathMeasure: PathMeasure
    var mCurAnimValue = 0F

    init {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 40F
        paint.setColor(Color.BLACK)

        val cx = 500f
        val cy = 500f
        val r  = 400f
        val oval = RectF(cx - r, cy - r, cx + r, cy + r)
        path.addArc(oval, -90F, -360F)
        pathMeasure = PathMeasure(path, true)
        val animator = ValueAnimator.ofFloat(0F,1F)
        animator.repeatCount = ValueAnimator.INFINITE
        animator.addUpdateListener { animation ->
            mCurAnimValue = animation.animatedValue as Float
            invalidate()
        }
        animator.duration = 20000
        animator.interpolator = LinearInterpolator()
        animator.start()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

//        基本图形绘制
//        val paint = Paint()
//        paint.setColor(Color.RED)
//        /*
//        画圆
//        paint.style = Paint.Style.STROKE
//        paint.strokeWidth = 50F
//
//        canvas.drawCircle(190F, 200F, 150F, paint)
//
//        paint.setColor(0x7EFFFF00)
//        canvas.drawCircle(190F, 200F, 100F, paint)
//        */
//        paint.style = Paint.Style.STROKE
//        paint.strokeWidth = 15F
//        canvas.drawLine(100F, 100F, 200F, 200F, paint) //画直线
//        canvas.drawPoint(300F, 300F, paint) //画点
//        canvas.drawRect(20F,20F,100F,100F,paint) //画矩形
//
//        paint.setColor(Color.argb(255,255,0,0)) //传入的四个参数分别为A(透明度),R(红),G(绿),B(蓝)

//        路径绘制
//        val paint = Paint().apply {
//            setColor(Color.RED)
//            style = Paint.Style.STROKE
//            strokeWidth = 5F
//        }

//        直线路径
//        val path = Path().apply {
//            moveTo(10F, 10F)
//            lineTo(10F, 100F)
//            lineTo(300F, 100F)
//            close()
//        }
//        canvas.drawPath(path, paint)

//        绘制弧形
//        val path = Path().apply {
//            moveTo(10F, 10F)
//        }
//        val rectF = RectF(100F,10F,200F,100F)
//
//        path.arcTo(rectF,0F,90F,true) //为true时默认将弧的起始点作为起始点
//        canvas.drawPath(path, paint)

//        paint.style = Paint.Style.FILL
//        val ovalPath = Path()
//        val rect = RectF(50F, 50F, 200F, 500F)
//        ovalPath.addOval(rect, Path.Direction.CCW) //构造一条椭圆路径
//        val rgn = Region()
//        rgn.setPath(ovalPath, Region(50, 50, 200, 200)) //setPath()函数中传入一个比椭圆区域小的矩形区域并取交集
//        rgn.union(Rect(10,10,50,300)) //将两个区域取并集

        /**
         * Op.INTERSECT 交集
         * Op.DIFFERENCE 补集
         * Op.REPLACE 替换
         * Op.REVERSE_DIFFERENCE 反转补集
         * Op.UNION 并集
         * Op.XOR 异并集
         */
//        val rect1 = Rect(100,100,400,200)
//        val rect2 = Rect(200,0,300,300)
//        val region1 = Region(rect1)
//        val region2 = Region(rect2)
//        region1.op(region2, Region.Op.INTERSECT) //取两个区域的交集

        val stop = pathMeasure.length * mCurAnimValue
        dst.reset()
        pathMeasure.getSegment(0F,stop,dst,true)
        canvas.drawPath(dst, paint)
    }

}