package com.rudachenkoroman.aston_intensiv__4.fragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.rudachenkoroman.aston_intensiv__4.R

class DFragment : Fragment(R.layout.fragment_d) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnFragmentBackDtoB = view.findViewById<Button>(R.id.btnFragmentBackDtoB)

        btnFragmentBackDtoB.setOnClickListener {
            parentFragmentManager.popBackStack(BFragment.B_FRAGMENT_TAG, 0)
        }
    }

    companion object {
        fun newInstance() = DFragment()
        const val D_FRAGMENT_TAG = "D_FRAGMENT_TAG"
    }
}