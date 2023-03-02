package com.example.calculaterofageinminutes

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvDate : TextView? = null
    private var tvMinute : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSelectData = findViewById<Button>(R.id.btSlecter)
        tvDate = findViewById(R.id.tvDate)
        tvMinute = findViewById(R.id.tvMinute)
        btnSelectData.setOnClickListener {
            CalendarSelected()
        }
    }

    private fun CalendarSelected(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dbg = DatePickerDialog(this,
            { _, year, month, day ->
                Toast.makeText(this, "That's work!", Toast.LENGTH_LONG).show()

                val formDate = "$day/${month + 1}/$year" // string with format of date

                tvDate?.text = formDate // transfer a string formDate

                val setOfDate = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH) //
                    setOfDate?.let {

                        val selectedDate = setOfDate.parse(formDate).time / 60000

                        val currentDate = setOfDate.parse(setOfDate.format(System.currentTimeMillis())).time / 60000
                        currentDate?.let {

                            val differenceInMinutes = currentDate - selectedDate

                            tvMinute?.text = differenceInMinutes.toString() }
                    }
            }, year, month, day
        )

        dbg.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dbg.show()
    }
}