package com.example.splitwise
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.fragment.app.Fragment
import com.example.splitwise.DataAccess.DatabaseInstance

class AccountFragment : Fragment(R.layout.activity_account) {
    private lateinit var auth: FirebaseAuth
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userNameText: TextView = view.findViewById(R.id.userName)
        val userEmailText: TextView = view.findViewById(R.id.userEmail)

        // Initialize FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Get the currently logged-in user's email
        val currentUser = auth.currentUser
        val userEmail = currentUser?.email

        if (userEmail != null) {
            // Retrieve the user from the database
            CoroutineScope(Dispatchers.IO).launch {
                val db = DatabaseInstance.getDatabase(requireContext())
                val userDao = db.userDao()
                val user = userDao.getUserByEmail(userEmail)

                withContext(Dispatchers.Main) {
                    if (user != null) {
                        userNameText.text = "${user.name}"
                        userEmailText.text = "${user.email}"
                    } else {
                        userNameText.text = "Temp User"
                        userEmailText.text = "Temp Email"
                    }
                }
            }
        }
    }
}