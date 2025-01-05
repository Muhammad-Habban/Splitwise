package com.example.splitwise

import android.widget.ImageView
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.splitwise.DAO.UserOweDetails
import com.example.splitwise.DataAccess.DatabaseInstance
import com.example.splitwise.Entities.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class SettleupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settle_up)
        val crossButton : ImageView = findViewById(R.id.crossButton)
        crossButton.setOnClickListener{
            finish()
        }
        val groupId = intent.getIntExtra("groupId", -1)
        val userOweListContainer: LinearLayout = findViewById(R.id.userOweListContainer)

        if (groupId == -1) {
            Toast.makeText(this, "Error: Invalid group!", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Fetch data for settlement
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val db = DatabaseInstance.getDatabase(applicationContext)
                val userExpenseDao = db.userExpenseDao()
                val userDao = db.userDao()

                val usersWhoOwe = userExpenseDao.getUsersWhoOweInGroup(groupId)

                withContext(Dispatchers.Main) {
                    populateUserOweList(userOweListContainer, usersWhoOwe, userDao)
                }
            } catch (e: Exception) {
                Log.e("SettleUpActivity", "Error fetching settlement data: ${e.message}")
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@SettleupActivity, "Failed to load settlement details.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun populateUserOweList(
        container: LinearLayout,
        userOweList: List<UserOweDetails>, // Pair of userId and amount owed
        userDao: com.example.splitwise.DAO.UserDao
    ) {
        container.removeAllViews()

        for ((userId, amountOwed) in userOweList) {
            val userOweCard = layoutInflater.inflate(R.layout.user_owe_card, container, false)

            val userNameTextView: TextView = userOweCard.findViewById(R.id.userName)
            val amountOwedTextView: TextView = userOweCard.findViewById(R.id.amountOwed)

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val user = userDao.getUserById(userId)
                    withContext(Dispatchers.Main) {
                        userNameTextView.text = user.name
                        amountOwedTextView.text = "PKR ${"%.2f".format(amountOwed)}"
                    }
                } catch (e: Exception) {
                    Log.e("UserOweCard", "Error fetching user details: ${e.message}")
                }
            }

            container.addView(userOweCard)
        }
    }
}