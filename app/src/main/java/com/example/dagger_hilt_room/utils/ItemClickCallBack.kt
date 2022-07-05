package com.example.dagger_hilt_room.utils

interface ItemClickCallBack {
    fun updateClick(position: Int)
    fun deleteClick(position: Int)
}