package com.example.splitwise
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment

class GroupFragment : Fragment(R.layout.activity_group) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = super.onCreateView(inflater, container, savedInstanceState)

        val photographyTripCard: ConstraintLayout? = view?.findViewById(R.id.photographyTripCard)

        photographyTripCard?.setOnClickListener {
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, GroupDetailFragment())
            transaction.commit()
        }
        val startNewGroupButton: Button? = view?.findViewById<Button>(R.id.startNewGroupButton)
        startNewGroupButton?.setOnClickListener{
            val intent = Intent(requireContext(), NewGroupActivity::class.java)
            startActivity(intent)
        }
        return view
    }
}