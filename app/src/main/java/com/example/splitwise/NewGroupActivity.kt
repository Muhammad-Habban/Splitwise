package com.example.splitwise

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import java.util.Calendar
import android.widget.Toast
import com.example.splitwise.DataAccess.DatabaseInstance
import com.example.splitwise.Entities.Group
import com.example.splitwise.Entities.GroupUserCrossRef
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewGroupActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    @SuppressLint("MissingInflatedId")
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
        auth = FirebaseAuth.getInstance()
        val crossButton : ImageView = findViewById(R.id.crossButton)
        crossButton.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        val groupNameInput: EditText = findViewById(R.id.descriptionInput)
        val doneText: Button = findViewById(R.id.doneButton)

        val currentUser = auth.currentUser
        val userEmail = currentUser?.email

        doneText.setOnClickListener {
            val groupName = groupNameInput.text.toString().trim()

            if (groupName.isEmpty()) {
                Toast.makeText(this, "Group name cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val db = DatabaseInstance.getDatabase(applicationContext)
                    val groupDao = db.groupDao()
                    val userDao = db.userDao()
                    val groupUserCrossRefDao = db.groupUserCrossRefDao()
                    val user = userEmail?.let { userDao.getUserByEmail(it) }
                    val currentUserId = user?.userId
                    // Insert the new group
                    val newGroup = Group(groupName = groupName)
                    val groupId = groupDao.insertGroup(newGroup).toInt() // Returns row ID as Long

                    // Insert a new row in GroupUserCrossRef for the logged-in user
                    val crossRef =
                        currentUserId?.let { it1 -> GroupUserCrossRef(userId = it1, groupId = groupId) }
                    if (crossRef != null) {
                        groupUserCrossRefDao.insertGroupUserCrossRef(crossRef)
                    }

                    runOnUiThread {
                        Toast.makeText(this@NewGroupActivity, "Group created successfully!", Toast.LENGTH_SHORT).show()
                        finish() // Close the activity
                    }
                } catch (e: Exception) {
                    runOnUiThread {
                        Toast.makeText(this@NewGroupActivity, "Failed to create group: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}