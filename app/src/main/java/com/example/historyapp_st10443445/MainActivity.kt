package com.example.historyapp_st10443445

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.accessibility.AccessibilityManager.AudioDescriptionRequestedChangeListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    //List of Historical events
    enum class HistoricalEvent(val year: Int, val description: String) {
        EVENT_1(99, "Bob Barker's passed away"),
        EVENT_2(95, "Nelson Mandela passed away"),
        EVENT_3(80, "John Lewis passed away"),
        EVENT_4(70, "Henry Ford II passed away"),
        EVENT_5(60, "Robin Williams passed away"),
        EVENT_6(50, "Michael Jackson passed away"),
        EVENT_7(40, "Paul Walker passed away"),
        EVENT_8(30, "Bob Marley passed away"),
        EVENT_9(20, "Cameron Boyce passed away"),
        EVENT_10(25, "Jimi Hendrix passed away"),

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //declaring variables
        val edtBirthYear = findViewById<EditText>(R.id.edtYear)
        val btnResults = findViewById<Button>(R.id.btnResults)
        val btnClear = findViewById<Button>(R.id.btnClear)
        val txtResults = findViewById<TextView>(R.id.txtResults)

        //If user press results button
        btnResults?.setOnClickListener ()
        {
            val birthYear = edtBirthYear.text.toString().toInt()

            if (birthYear != null && birthYear in 20 .. 100) {
                val eventYears = HistoricalEvent.values().map { it.year }

                val events = when(birthYear)
                {
                    //This statement will show if users age is the same as the death historical event
                    in eventYears -> {
                        val event = HistoricalEvent.values().find { it.year == birthYear }
                        listOf("In $birthYear: ${event?.description ?: "event"}")
                    }
                    //This statemant will shoe if your age is one year before historical event
                    in eventYears.map { it - 1 } -> {
                        val event = HistoricalEvent.values().find { it.year == birthYear + 1 }
                        listOf("Your birth year is one year after the Historical event" + "${event?.description ?: "event"}")
                    }
                    //This statemant will show if your age is one year after historical event
                    in eventYears.map { it + 1 } -> {
                        val event = HistoricalEvent.values().find { it.year == birthYear - 1 }
                        listOf("Your birth year is one year before the Historical event" + "${event?.description ?: "event"}")
                    }
                    else -> listOf("No History events found for $birthYear")
                }
                txtResults.text = events.joinToString("\n")
            }else {
                txtResults.text = "No event has been found from input of your age."
            }
        }
        //when you press the clear button
        btnClear?.setOnClickListener(){
            edtBirthYear.text.clear()
            txtResults.text = ""
        }
    }

}