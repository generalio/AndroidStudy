package com.generlas.broadcaststudy

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import android.widget.TimePicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var calendarView: CalendarView
    lateinit var textView: TextView
    lateinit var timeButton: Button
    lateinit var dateButton: Button

    @SuppressLint("SetTextI18n", "DefaultLocale", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calendarView = findViewById(R.id.calendarView)
        textView = findViewById(R.id.textView)
        timeButton = findViewById(R.id.main_button)
        dateButton = findViewById(R.id.btn_main_dateSelect)

        //日期选择
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val selectDate = "$dayOfMonth/${month + 1}/$year"
            textView.text = selectDate
        }

        dateButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // 日期dialog
            val datePickerDialog = DatePickerDialog(
                this,
                {_, selectedYear, selectedMonth, selectedDay ->
                    val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                    textView.text = selectedDate
                },
                year,
                month,
                day
            )

            datePickerDialog.show()
        }

        //时间选择
        timeButton.setOnClickListener {

            //跳转到时间选择页面
            /*val intent = Intent(this,TimepickActivity::class.java)
            startActivity(intent)*/

            //时间选择弹窗
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE) //获取默认当前时间

            //打开弹窗
            val timePickerDialog = TimePickerDialog(this, {_, selectedHour,selectedMinute ->
                val selectedTime = String.format("%02d:%02d",selectedHour,selectedMinute) //获取时间具体信息字符串，若不满两位自动补满
                textView.text = "Selected Time: $selectedTime"
            },hour,minute,true)

            timePickerDialog.show()
        }

    }
}