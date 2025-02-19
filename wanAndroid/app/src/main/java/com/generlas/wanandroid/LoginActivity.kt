package com.generlas.wanandroid

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {

    lateinit var mTvUsername: TextView
    lateinit var mTvPassword: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val mBtnLogin : Button = findViewById(R.id.btn_login_login)
        mTvUsername = findViewById(R.id.tv_login_username)
        mTvPassword = findViewById(R.id.tv_login_password)
        mBtnLogin.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_login, null, true)
        val mEtUsername : EditText = dialogView.findViewById(R.id.et_login_username)
        val mEtPassword: EditText = dialogView.findViewById(R.id.et_login_password)
        val dialogBuilder = AlertDialog.Builder(this)
            .setTitle("登录")
            .setView(dialogView)
            .setPositiveButton("Login") {dialog, which -> loginSucceed(mEtUsername.text.toString(), mEtPassword.text.toString())}
            .setNegativeButton("Cancel") {dialog, which ->}
        dialogBuilder.show()
    }

    private fun loginSucceed(username: String , password: String) {
        mTvUsername.text = username
        mTvPassword.text = password
    }
}