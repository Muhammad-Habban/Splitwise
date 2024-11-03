package com.example.splitwise

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Load the default fragment (e.g., GroupFragment)
        loadFragment(GroupFragment())

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_groups -> {
                    loadFragment(GroupFragment())
                    true
                }
                R.id.nav_friends -> {
                    loadFragment(FriendFragment())
                    true
                }
                R.id.nav_activity -> {
                    loadFragment(ActivityFragment())
                    true
                }
                R.id.nav_account -> {
                    loadFragment(AccountFragment())
                    true
                }
                else -> false
            }
        }

        val sharedButton : FloatingActionButton = findViewById(R.id.sharedButton)
        sharedButton.setOnClickListener{
            val intent = Intent(this, AddExpenseActivity::class.java)
            startActivity(intent)
        }

    }

    // Helper function to load fragments
    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}