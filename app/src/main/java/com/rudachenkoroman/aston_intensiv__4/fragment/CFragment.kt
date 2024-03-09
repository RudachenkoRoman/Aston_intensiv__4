package com.rudachenkoroman.aston_intensiv__4.fragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.rudachenkoroman.aston_intensiv__4.R

class CFragment : Fragment(R.layout.fragment_c){

    private var message: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            message = it.getString(MESSAGE)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnFragmentCtoD = view.findViewById<Button>(R.id.btnFragmentCtoD)
        val btnFragmentBackCtoA = view.findViewById<Button>(R.id.btnFragmentBackCtoA)
        val textFragmentBtoC = view.findViewById<TextView>(R.id.textFragmentBtoC)

        textFragmentBtoC.text = message

        btnFragmentCtoD.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(
                R.id.containerFragment, DFragment.newInstance(), DFragment.D_FRAGMENT_TAG
            ).addToBackStack(DFragment.D_FRAGMENT_TAG).setReorderingAllowed(true).commit()
        }

        btnFragmentBackCtoA.setOnClickListener {
            parentFragmentManager.popBackStack(AFragment.A_FRAGMENT_TAG, 0)
        }
    }

    companion object {
        private const val MESSAGE = "MESSAGE"
        fun newInstance(message: String) = CFragment().apply {
            arguments = Bundle().apply {
                putString(MESSAGE, message)
            }
        }

        const val C_FRAGMENT_TAG = "C_FRAGMENT_TAG"
    }
}