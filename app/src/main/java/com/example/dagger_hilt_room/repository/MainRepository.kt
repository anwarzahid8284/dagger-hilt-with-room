package com.example.dagger_hilt_room.repository

import com.example.dagger_hilt_room.room.User
import com.example.dagger_hilt_room.room.UserDao
import javax.inject.Inject

class MainRepository @Inject constructor(private val userDao: UserDao) {

    suspend fun addUser(user: User) = userDao.addUser(user)

    suspend fun deleteUser(user: User) = userDao.deleteUser(user)

    suspend fun updateUser(id:Int,userName:String,userDesignation:String) = userDao.updateUser(id,userName,userDesignation)

     fun showUser() = userDao.showUser()
}