package com.rudachenkoroman.aston_intensiv__4.fragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.rudachenkoroman.aston_intensiv__4.R

class AFragment : Fragment(R.layout.fragment_a){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnFragmentAtoB = view.findViewById<Button>(R.id.btnFragmentAtoB)
        btnFragmentAtoB.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.containerFragment, BFragment.newInstance(), BFragment.B_FRAGMENT_TAG)
                .addToBackStack(BFragment.B_FRAGMENT_TAG)
                .setReorderingAllowed(true)
                .commit()
        }
    }

    companion object {
        fun newInstance() = AFragment()
        const val A_FRAGMENT_TAG = "A_FRAGMENT_TAG"
    }
}