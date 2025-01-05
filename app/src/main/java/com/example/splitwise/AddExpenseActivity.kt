package com.example.splitwise

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.splitwise.DataAccess.DatabaseInstance
import com.example.splitwise.Entities.Expense
import com.example.splitwise.Entities.UserExpense
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.google.firebase.auth.FirebaseAuth

class AddExpenseActivity : AppCompatActivity() {
    private var groupId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addexpense)
        // Get the group ID from the intent
        groupId = intent.getIntExtra("groupId", -1)

        val expenseNameInput: EditText = findViewById(R.id.descriptionInput)
        val expenseAmountInput: EditText = findViewById(R.id.amountInput)
        val addExpenseButton: Button = findViewById(R.id.addExpenseButton)

        addExpenseButton.setOnClickListener {
            val expenseName = expenseNameInput.text.toString().trim()
            val expenseAmount = expenseAmountInput.text.toString().trim().toDoubleOrNull()

            if (expenseName.isEmpty() || expenseAmount == null || expenseAmount <= 0) {
                Toast.makeText(this, "Please enter valid details", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            addExpenseToDatabase(expenseName, expenseAmount)
        }
        val crossButton : ImageView = findViewById(R.id.crossButton)
        crossButton.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
    private fun addExpenseToDatabase(expenseName: String, expenseAmount: Double) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val db = DatabaseInstance.getDatabase(applicationContext)
                val expenseDao = db.expenseDao()
                val userExpenseDao = db.userExpenseDao()
                val groupUserCrossRefDao = db.groupUserCrossRefDao()
                val userDao = db.userDao()

                // Get current user ID
                val auth = FirebaseAuth.getInstance()
                val currentUser = auth.currentUser
                val userEmail = currentUser?.email
                val user = userEmail?.let { userDao.getUserByEmail(it) }
                val currentUserId = user?.userId

                if (currentUserId != null) {
                    // Insert expense into Expense table
                    val newExpense = Expense(
                        groupId = groupId,
                        amount = expenseAmount,
                        description = expenseName,
                        paidByUserId = currentUserId
                    )
                    val expenseId = expenseDao.insertExpense(newExpense).toInt()

                    // Get all users in the group
                    val groupMembers = groupUserCrossRefDao.getUsersInGroup(groupId)

                    // Split expense equally among group members
                    val splitAmount = expenseAmount / groupMembers.size

                    // Insert UserExpense records
                    for (member in groupMembers) {
                        val userExpense = UserExpense(
                            payerUserId = currentUserId,
                            debtorUserId = member,
                            groupId = groupId,
                            amount = splitAmount
                        )
                        userExpenseDao.insertUserExpense(userExpense)
                    }

                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@AddExpenseActivity, "Expense added successfully!", Toast.LENGTH_SHORT).show()
                        finish() // Close the activity
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@AddExpenseActivity, "Failed to add expense: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}