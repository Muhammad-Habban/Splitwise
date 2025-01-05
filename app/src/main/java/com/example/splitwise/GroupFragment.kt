package com.example.splitwise
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.splitwise.DataAccess.DatabaseInstance
import com.example.splitwise.Entities.Group
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GroupFragment : Fragment(R.layout.activity_group) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = super.onCreateView(inflater, container, savedInstanceState)

        val groupListContainer: LinearLayout? = view?.findViewById(R.id.groupListContainer)

        // Fetch groups from database
        CoroutineScope(Dispatchers.IO).launch {
            val db = DatabaseInstance.getDatabase(requireContext())
            val userDao = db.userDao()
            val groupDao = db.groupDao()
            val groupUserCrossRefDao = db.groupUserCrossRefDao()

            val auth = FirebaseAuth.getInstance()
            val currentUser = auth.currentUser
            val userEmail = currentUser?.email
            val user = userEmail?.let { userDao.getUserByEmail(it) }
            val currentUserId = user?.userId
            if (currentUserId != null) {
                val groupIds = groupUserCrossRefDao.getGroupsForUser(currentUserId)

                val groups = groupDao.getAllGroups().filter { group -> group.groupId in groupIds }

                withContext(Dispatchers.Main) {
                    populateGroupList(groupListContainer, groups)
                }
            }
        }

        val startNewGroupButton: Button? = view?.findViewById<Button>(R.id.startNewGroupButton)
        startNewGroupButton?.setOnClickListener{
            val intent = Intent(requireContext(), NewGroupActivity::class.java)
            startActivity(intent)
        }
        return view
    }
    private fun populateGroupList(container: LinearLayout?, groups: List<Group>) {
        container?.removeAllViews()

        for (group in groups) {
            val groupCard = layoutInflater.inflate(R.layout.group_card, container, false) as ConstraintLayout

            val groupTitle: TextView = groupCard.findViewById(R.id.groupTitle)
            val groupDebt: TextView = groupCard.findViewById(R.id.groupDebt)

            val auth: FirebaseAuth = FirebaseAuth.getInstance()

            // Get the currently logged-in user's email
            val currentUser = auth.currentUser
            val userEmail = currentUser?.email
            groupTitle.text = group.groupName
            CoroutineScope(Dispatchers.IO).launch {
                val db = DatabaseInstance.getDatabase(requireContext())
                val userExpenseDao = db.userExpenseDao()
                val userDao = db.userDao()
                val user = userEmail?.let { userDao.getUserByEmail(it) }
                val currentUserId = user?.userId
                val totalDebt =
                    currentUserId?.let { userExpenseDao.getTotalDebtForUserInGroup(it, group.groupId) } ?: 0.0

                withContext(Dispatchers.Main) {
                    groupDebt.text = "You owe PKR${"%.2f".format(totalDebt)}"
                }
            }

            groupCard.setOnClickListener {
                val transaction = parentFragmentManager.beginTransaction()
                val groupDetailFragment = GroupDetailFragment().apply {
                    arguments = Bundle().apply {
                        putInt("groupId", group.groupId)
                    }
                }
                transaction.replace(R.id.fragment_container, groupDetailFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }

            container?.addView(groupCard)
        }
    }
}