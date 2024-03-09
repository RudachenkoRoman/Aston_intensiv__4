package com.rudachenkoroman.aston_intensiv__4.fragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.rudachenkoroman.aston_intensiv__4.R

class BFragment : Fragment(R.layout.fragment_b) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnFragmentBtoC = view.findViewById<Button>(R.id.btnFragmentBtoC)
        val btnFragmentBackBtoA = view.findViewById<Button>(R.id.btnFragmentBackBtoA)

        btnFragmentBtoC.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.containerFragment,
                    CFragment.newInstance("Hello Fragment C!!!"),
                    CFragment.C_FRAGMENT_TAG
                )
                .addToBackStack(CFragment.C_FRAGMENT_TAG)
                .setReorderingAllowed(true)
                .commit()
        }

        btnFragmentBackBtoA.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }


    companion object {
        fun newInstance() = BFragment()
        const val B_FRAGMENT_TAG = "B_FRAGMENT_TAG"
    }
}