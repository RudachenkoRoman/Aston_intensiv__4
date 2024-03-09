package com.rudachenkoroman.aston_intensiv__4.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rudachenkoroman.aston_intensiv__4.data.User
import com.rudachenkoroman.aston_intensiv__4.data.UserData

class UsersViewModel() : ViewModel() {

    private val repo: UserData = UserData()
    private val _data = MutableLiveData<List<User>>()
    val data: LiveData<List<User>> = _data

    fun getData() {
        _data.value = repo.getUsers()
    }

    fun updateUser(updatedUser: User) {
        val currentUsers = _data.value.orEmpty().toMutableList()
        val index = currentUsers.indexOfFirst { it.id == updatedUser.id }
        if (index != -1) {
            currentUsers[index] = updatedUser
            _data.value = currentUsers
        }
    }
}