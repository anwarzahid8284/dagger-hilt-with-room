package com.example.dagger_hilt_room.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.dagger_hilt_room.R
import com.example.dagger_hilt_room.databinding.UserItemBinding
import com.example.dagger_hilt_room.room.User
import com.example.dagger_hilt_room.utils.ItemClickCallBack

class UserAdapter(private val listener: ItemClickCallBack) :
    RecyclerView.Adapter<UserAdapter.DataVHolder>() {
    val userList = ArrayList<User>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataVHolder {

        return DataVHolder(
            UserItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DataVHolder, position: Int) {
        holder.bindData(userList[holder.adapterPosition])
    }

    override fun getItemCount(): Int = userList.size
    inner class DataVHolder(private val userItemBinding: UserItemBinding) :
        RecyclerView.ViewHolder(userItemBinding.root), View.OnClickListener {
        init {
            userItemBinding.imageViewID.setOnClickListener(this)
        }

        fun bindData(user: User) {
            userItemBinding.nameID.text = user.userName
            userItemBinding.designationID.text = user.userDesignation
        }

        override fun onClick(v: View?) {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                v?.let {
                    showPop(adapterPosition,v,v.context)
                }

            }
        }

    }

    fun showPop(position: Int,view: View,context: Context) {
        val popupMenu = PopupMenu(context, view)
        val inflater = popupMenu.menuInflater
        inflater.inflate(R.menu.drop_down_menu, popupMenu.menu)
        popupMenu.show()
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.updateUser -> listener.updateClick(position)
                R.id.deleteUser -> listener.deleteClick(position)
                else -> false
            }
            true
        }
    }

    fun showUser(list: List<User>) {
        userList.clear()
        userList.addAll(list)
        notifyDataSetChanged()
    }

}