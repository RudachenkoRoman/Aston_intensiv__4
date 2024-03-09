package com.rudachenkoroman.aston_intensiv__4.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.rudachenkoroman.aston_intensiv__4.R
import com.rudachenkoroman.aston_intensiv__4.adapter.UserListAdapter
import com.rudachenkoroman.aston_intensiv__4.data.User
import com.rudachenkoroman.aston_intensiv__4.fragment.UserDetailsFragment.Companion.USER_DETAILS_FRAGMENT_TAG
import com.rudachenkoroman.aston_intensiv__4.parcelable
import com.rudachenkoroman.aston_intensiv__4.viewModel.UsersViewModel

class UserListFragment : Fragment(R.layout.fragment_user_list) {

    private lateinit var viewModel: UsersViewModel
    private val mAdapter = UserListAdapter(
        onClick = { item -> onUserClick(item) }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initRecycler()
        initViewModel()
    }

    private fun initListeners() {
        parentFragmentManager.setFragmentResultListener(
            FRAGMENT_RESULT_KEY_UPDATE_USER, this
        ) { _, bundle ->
            val user = bundle.parcelable<User>(USER_BUNDLE_KEY)
            if (user != null) {
                viewModel.updateUser(user)
            }
        }
    }

    private fun initRecycler() {
        view?.findViewById<RecyclerView>(R.id.recyclerView)?.adapter = mAdapter
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[UsersViewModel::class.java]
        viewModel.data.observe(requireActivity()) { state ->
            state?.let {
                renderRecyclerData(state)
            }
        }
        viewModel.getData()
    }

    private fun renderRecyclerData(newList: List<User>) {
        mAdapter.submitList(newList)
    }

    private fun onUserClick(item: User) {
        parentFragmentManager.beginTransaction()
            .replace(
                R.id.containerFragment,
                UserDetailsFragment.newInstance(item),
                USER_DETAILS_FRAGMENT_TAG,
            )
            .addToBackStack(USER_DETAILS_FRAGMENT_TAG)
            .setReorderingAllowed(true)
            .commit()
    }

    companion object {
        const val USER_LIST_FRAGMENT_TAG = "USER_LIST_FRAGMENT_TAG"
        const val FRAGMENT_RESULT_KEY_UPDATE_USER = "FRAGMENT_RESULT_KEY_UPDATE_USER"
        const val USER_BUNDLE_KEY = "USER_BUNDLE_KEY"
    }
}