package com.example.dagger_hilt_room.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dagger_hilt_room.R
import com.example.dagger_hilt_room.databinding.ActivityMainBinding
import com.example.dagger_hilt_room.databinding.AddUserDialogBinding
import com.example.dagger_hilt_room.databinding.UpdateUserDialogBinding
import com.example.dagger_hilt_room.room.User
import com.example.dagger_hilt_room.utils.ItemClickCallBack
import com.example.dagger_hilt_room.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ItemClickCallBack {
    private var activityMainBinding: ActivityMainBinding? = null
    private var userAdapter = UserAdapter(this)
    private val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = activityMainBinding?.root
        setContentView(view)
        setAdapter()
        userViewModel.showUser().observe(this) {
            userAdapter.showUser(it)
        }

    }


    private fun setAdapter() {
        activityMainBinding?.recyclerViewID?.adapter = userAdapter
        activityMainBinding?.recyclerViewID?.addItemDecoration(
            DividerItemDecoration(
                this@MainActivity,
                LinearLayoutManager.VERTICAL
            )
        )
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (R.id.addUser == item.itemId) {
            addUserDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    private fun addUserDialog() {
        val addLayout = AddUserDialogBinding.inflate(LayoutInflater.from(this))
        val addUserDia: AlertDialog = AlertDialog.Builder(this).create()
        addUserDia.setView(addLayout.root)
        addUserDia.show()
        addLayout.addUserBtnID.setOnClickListener {
            val name = addLayout.enterNameID.text.toString().trim()
            val designation = addLayout.enterDesignationID.text.toString().trim()
            if (name.isNotEmpty() && designation.isNotEmpty()) {
                lifecycleScope.launch {
                    userViewModel.addUser(User(name, designation))
                }
                addUserDia.dismiss()
            } else {
                Toast.makeText(this, "Please enter both field", Toast.LENGTH_SHORT).show()
            }
        }
        addLayout.cancelBtnID.setOnClickListener { addUserDia.dismiss() }

    }

    private fun updateUser(position: Int) {
        val updateLayout = UpdateUserDialogBinding.inflate(LayoutInflater.from(this))
        val updateUserDia: AlertDialog = AlertDialog.Builder(this).create()
        updateUserDia.setView(updateLayout.root)
        updateUserDia.show()
        updateLayout.updateUserBtnID.setOnClickListener {
            val name = updateLayout.updateNameID.text.toString().trim()
            val designation = updateLayout.updateDesignationID.text.toString().trim()
            lifecycleScope.launch {
                userViewModel.updateUser(userAdapter.userList[position].userID, name, designation)
            }
            updateUserDia.dismiss()
        }
        updateLayout.cancelBtnID.setOnClickListener {
            updateUserDia.dismiss()
        }


    }

    private fun deleteUser(position: Int) {
        lifecycleScope.launch {
            userViewModel.deleteUser(userAdapter.userList[position])
        }

    }


    override fun updateClick(position: Int) {
        updateUser(position)
    }

    override fun deleteClick(position: Int) {
        deleteUser(position)
    }
}