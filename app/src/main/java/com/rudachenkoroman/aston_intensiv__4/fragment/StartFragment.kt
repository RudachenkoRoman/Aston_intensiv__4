package com.rudachenkoroman.aston_intensiv__4.fragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.rudachenkoroman.aston_intensiv__4.R

class StartFragment : Fragment(R.layout.fragment_start){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnHomeWorkOne = view.findViewById<Button>(R.id.btnHomeWorkOne)
        val btnHomeWorkTwo = view.findViewById<Button>(R.id.btnHomeWorkTwo)

        btnHomeWorkOne.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.containerFragment, AFragment.newInstance(), AFragment.A_FRAGMENT_TAG)
                .addToBackStack(AFragment.A_FRAGMENT_TAG).setReorderingAllowed(true).commit()
        }

        btnHomeWorkTwo.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.containerFragment, UserListFragment(), UserListFragment.USER_LIST_FRAGMENT_TAG)
                .addToBackStack(UserListFragment.USER_LIST_FRAGMENT_TAG)
                .setReorderingAllowed(true)
                .commit()
        }
    }
}