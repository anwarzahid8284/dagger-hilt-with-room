package com.example.dagger_hilt_room.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @ColumnInfo(name = "user_name")
    val userName: String,
    @ColumnInfo(name = "user_designation")
    val userDesignation: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    var userID:Int = 0
}
