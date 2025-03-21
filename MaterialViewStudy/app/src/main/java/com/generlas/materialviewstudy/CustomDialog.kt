package com.generlas.materialviewstudy

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatImageView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * description ：自定义一个BottomSheetDialog
 * date : 2025/3/21 09:11
 */
class CustomDialog : BottomSheetDialogFragment() {

    private var height: Int = 0

    //工厂模式返回一个新的CustomDialog实例
    fun newInstance(): CustomDialog {
        return CustomDialog()
    }

    //设置初始高度
    fun setDialogHeight(height: Int) : CustomDialog {
        this.height = height
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.StyleBottomSheetDialogBg) //设置圆角样式
    }

    override fun onStart() {
        super.onStart()

        //拿到系统的bottom_sheet
        val bottomSheetDialog = (dialog as BottomSheetDialog)
        val view = bottomSheetDialog.delegate.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)!!
        val behavior = BottomSheetBehavior.from(view)

        //设置弹出高度
        behavior.peekHeight = height
        view.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT //设置dialog高度
        behavior.isHideable = false
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        //加载布局
        val view = LayoutInflater.from(context).inflate(R.layout.dialog, null)
        dialog.setContentView(view)
        //设置布局的点击事件
        val mIvClose: AppCompatImageView = view.findViewById(R.id.iv_close)
        mIvClose.setOnClickListener {
            dismiss()
        }
        return dialog
    }

}