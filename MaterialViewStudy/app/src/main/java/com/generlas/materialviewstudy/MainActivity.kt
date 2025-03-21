package com.generlas.materialviewstudy

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    lateinit var mBtnDialog: MaterialButton
    private var dialogHeight : Int = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBtnDialog = findViewById(R.id.btn_dialog)
        mBtnDialog.setOnClickListener {
            val dialog  = CustomDialog().newInstance()
                .setDialogHeight(dialogHeight)
            val ft: FragmentTransaction =
                supportFragmentManager.beginTransaction()
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE) //设置过渡动画
            dialog.show(ft, "DialogMore") //开启bottomSheetDialog
        }
    }
}