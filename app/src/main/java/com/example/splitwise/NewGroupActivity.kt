package com.example.splitwise

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class NewGroupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newgroup)
        val startDateInput: EditText = findViewById(R.id.startDateInput)
        val endDateInput: EditText = findViewById(R.id.endDateInput)

        startDateInput.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDay ->
                    val selectedDate = "${selectedDay}/${selectedMonth + 1}/${selectedYear}"
                    startDateInput.setText(selectedDate)
                },
                year, month, day
            )
            datePickerDialog.show()
        }
        endDateInput.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDay ->
                    val selectedDate = "${selectedDay}/${selectedMonth + 1}/${selectedYear}"
                    endDateInput.setText(selectedDate)
                },
                year, month, day
            )
            datePickerDialog.show()
        }

        val crossButton : ImageView = findViewById(R.id.crossButton)
        crossButton.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}