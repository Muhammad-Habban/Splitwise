package com.example.splitwise

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.splitwise.DataAccess.DatabaseInstance
import com.example.splitwise.Entities.GroupUserCrossRef
import com.example.splitwise.Entities.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShowAllUsersActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_all_users)

        val groupId = intent.getIntExtra("groupId", -1)
        if (groupId == -1) {
            Toast.makeText(this, "Invalid group ID", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val usersContainer: LinearLayout = findViewById(R.id.usersContainer)

        CoroutineScope(Dispatchers.IO).launch {
            val db = DatabaseInstance.getDatabase(applicationContext)
            val userDao = db.userDao()
            val groupUserCrossRefDao = db.groupUserCrossRefDao()
            val allUsers = userDao.getAllUsers()
            val groupUserIds = groupUserCrossRefDao.getUsersInGroup(groupId)

            auth = FirebaseAuth.getInstance()
            val currentUser = auth.currentUser
            val currentUserEmail = currentUser?.email
            val currentUserEntity = currentUserEmail?.let { userDao.getUserByEmail(it) }

            // Filter out the current user and users already in the group
            val filteredUsers = allUsers.filter { user ->
                user.userId != currentUserEntity?.userId && !groupUserIds.contains(user.userId)
            }
            withContext(Dispatchers.Main) {
                displayUsers(usersContainer, filteredUsers, groupId)
            }
        }
    }

    private fun displayUsers(container: LinearLayout, users: List<User>, groupId: Int) {
        container.removeAllViews()

        for (user in users) {
            val userCard = layoutInflater.inflate(R.layout.user_card, container, false)
            val userNameTextView = userCard.findViewById<TextView>(R.id.userNameTextView)
            val addButton = userCard.findViewById<Button>(R.id.addButton)

            userNameTextView.text = user.name

            addButton.setOnClickListener {
                addUserToGroup(user.userId, groupId)
                finish()
            }

            container.addView(userCard)
        }
    }

    private fun addUserToGroup(userId: Int, groupId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val db = DatabaseInstance.getDatabase(applicationContext)
                val groupUserCrossRefDao = db.groupUserCrossRefDao()

                val crossRef = GroupUserCrossRef(userId = userId, groupId = groupId)
                groupUserCrossRefDao.insertGroupUserCrossRef(crossRef)

                withContext(Dispatchers.Main) {
                    Toast.makeText(this@ShowAllUsersActivity, "User added to group!", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@ShowAllUsersActivity, "Failed to add user: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
