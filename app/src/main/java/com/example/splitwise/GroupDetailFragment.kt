package com.example.splitwise

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.example.splitwise.DataAccess.DatabaseInstance
import android.widget.LinearLayout
import android.widget.TextView
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Button
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.splitwise.Entities.Expense
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GroupDetailFragment : Fragment(R.layout.activity_groupdetail) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        val backButton: ImageView? = view?.findViewById(R.id.backButton)

        // Retrieve groupId from arguments
        val groupId = arguments?.getInt("groupId") ?: -1

        backButton?.setOnClickListener {
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, GroupFragment())
            transaction.commit()
        }

        val settleUpButton : Button? = view?.findViewById(R.id.settleUpButton)
        settleUpButton?.setOnClickListener{
            val intent = Intent(requireContext(), SettleupActivity::class.java)
            intent.putExtra("groupId", groupId)
            startActivity(intent)
        }
        val balanceButton : Button? = view?.findViewById(R.id.balancesButton)
        balanceButton?.setOnClickListener{
            val intent = Intent(requireContext(), BalanceActivity::class.java)
            startActivity(intent)
        }
        val totalButton : Button? = view?.findViewById(R.id.totalsButton)
        totalButton?.setOnClickListener{
            val intent = Intent(requireContext(), TotalsActivity::class.java)
            startActivity(intent)
        }
        val whiteboardButton : Button? = view?.findViewById(R.id.whiteboardButton)
        whiteboardButton?.setOnClickListener{
            val intent = Intent(requireContext(), WhiteboardActivity::class.java)
            startActivity(intent)
        }

        val sharedButton : FloatingActionButton? = view?.findViewById(R.id.sharedButton)
        sharedButton?.setOnClickListener{
            val intent = Intent(requireContext(), AddExpenseActivity::class.java)
            intent.putExtra("groupId", groupId)
            startActivity(intent)
        }

        val addUsersButton: Button? = view?.findViewById(R.id.addUsersButton)
        addUsersButton?.setOnClickListener {
            val intent = Intent(requireContext(), ShowAllUsersActivity::class.java)
            intent.putExtra("groupId", groupId) // Pass the group ID to the activity
            startActivity(intent)
        }


        val groupNameTextView: TextView? = view?.findViewById(R.id.groupName)
        val expenseListContainer: LinearLayout? = view?.findViewById(R.id.expenseListContainer)




        if (groupId == -1) {
            Toast.makeText(requireContext(), "Error: Group not found!", Toast.LENGTH_SHORT).show()
            return view
        }

        // Fetch group details and expenses
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val db = DatabaseInstance.getDatabase(requireContext())
                val groupDao = db.groupDao()
                val expenseDao = db.expenseDao()
                val userDao = db.userDao()

                // Fetch group details
                val group = groupDao.getGroupById(groupId)

                // Fetch expenses for the group
                val expenses = expenseDao.getExpensesByGroupId(groupId)

                withContext(Dispatchers.Main) {
                    groupNameTextView?.text = group.groupName
                    populateExpenseList(expenseListContainer, expenses, userDao)
                }
            } catch (e: Exception) {
                Log.e("GroupDetailFragment", "Error fetching group details: ${e.message}")
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Failed to load group details.", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return view
    }
    private fun populateExpenseList(
        container: LinearLayout?,
        expenses: List<Expense>,
        userDao: com.example.splitwise.DAO.UserDao
    ) {
        container?.removeAllViews()

        for (expense in expenses) {
            val expenseCard =
                layoutInflater.inflate(R.layout.expense_card, container, false) as ConstraintLayout

            val descriptionTextView: TextView = expenseCard.findViewById(R.id.expenseDescription)
            val paidByTextView: TextView = expenseCard.findViewById(R.id.expensePaidBy)
            val amountTextView: TextView = expenseCard.findViewById(R.id.expenseAmount)

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val user = userDao.getUserById(expense.paidByUserId)
                    withContext(Dispatchers.Main) {
                        descriptionTextView.text = expense.description
                        paidByTextView.text = "Paid by ${user.name}"
                        amountTextView.text = "PKR ${"%.2f".format(expense.amount)}"
                    }
                } catch (e: Exception) {
                    Log.e("ExpenseCard", "Error fetching user details: ${e.message}")
                }
            }

            container?.addView(expenseCard)
        }
    }
}