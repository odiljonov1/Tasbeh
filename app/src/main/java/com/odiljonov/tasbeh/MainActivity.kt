package com.odiljonov.tasbeh

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime
import java.time.chrono.HijrahDate
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    lateinit var context: Context

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val sharedPreferences: SharedPreferences = getPreferences(Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        var number = sharedPreferences.getInt("timesRepeated", 0)

        txtTotal.text = number.toString()

        context = this

        imgPressCount.setOnClickListener {
            number++

            editor.clear()
            editor.putInt("timesRepeated", number)
            editor.apply()
            editor.commit()

            number = sharedPreferences.getInt("timesRepeated", 0)

            txtTotal.text = number.toString()

        }

        imgRestart.setOnClickListener {
            number = 0
            txtTotal.text = number.toString()

        }
        val latinedate = LocalDateTime.now()
        val hijrah = HijrahDate.now()
        val formatter = DateTimeFormatter.ofPattern("dd EEE MMM yyyy")
        txtDateLatine.text = latinedate.format(formatter)
        txtDateArabic.text = hijrah.format(formatter)
    }
}