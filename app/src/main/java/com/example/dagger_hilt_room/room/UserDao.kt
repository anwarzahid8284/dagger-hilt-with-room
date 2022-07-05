package com.example.dagger_hilt_room.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert
    suspend fun addUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("UPDATE user SET user_name =:name, user_designation=:designation where user_id=:id")
    suspend fun updateUser(id: Int,name:String,designation:String)

    @Query("select * from user")
    fun showUser(): LiveData<List<User>>
}