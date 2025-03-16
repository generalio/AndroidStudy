package com.generlas.broadcaststudy

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import android.widget.TimePicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TimepickActivity : AppCompatActivity() {

    lateinit var timePick: TimePicker
    lateinit var timeText: TextView


    @SuppressLint("DefaultLocale", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timepick)

        timePick = findViewById(R.id.timePick)
        timeText = findViewById(R.id.time_pickText)

        timePick.setOnTimeChangedListener { timePicker, hourOfDay, minute ->
            val selectedTime = String.format("%02d:%02d",hourOfDay,minute)
            timeText.text = "Selected Time: $selectedTime"
        }

    }
}