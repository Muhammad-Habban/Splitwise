package com.example.splitwise

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment

class GroupDetailFragment : Fragment(R.layout.activity_groupdetail) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        val backButton: ImageView? = view?.findViewById(R.id.backButton)

        backButton?.setOnClickListener {
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, GroupFragment())
            transaction.commit()
        }

        val settleUpButton : Button? = view?.findViewById(R.id.settleUpButton)
        settleUpButton?.setOnClickListener{
            val intent = Intent(requireContext(), SettleupActivity::class.java)
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
        return view
    }
}