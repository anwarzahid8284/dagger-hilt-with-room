package com.example.dagger_hilt_room.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dagger_hilt_room.repository.MainRepository
import com.example.dagger_hilt_room.room.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    suspend fun addUser(user: User) {
        repository.addUser(user = user)
    }

    suspend fun deleteUser(user: User) {
        repository.deleteUser(user = user)
    }

    suspend fun updateUser(id:Int,userName:String,userDesignation:String) {
        repository.updateUser(id,userName,userDesignation)
    }

    fun showUser(): LiveData<List<User>> {
        return repository.showUser()
    }
}