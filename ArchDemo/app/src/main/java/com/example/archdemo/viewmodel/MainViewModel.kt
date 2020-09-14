package com.example.archdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.archdemo.model.User

class MainViewModel : ViewModel() {

    val userLiveData = MutableLiveData<ArrayList<User>>()
    private val originalUserList = ArrayList<User>()
    private val userList = ArrayList<User>()

    fun load(){
        originalUserList.addAll(dataSet())
    }

    private fun dataSet(): ArrayList<User> {
        val user1 = User("Thiago Alcantra", "Midfielder", "")
        val user2 = User("Lionel Messi", "Center Forward", "")
        val user3 = User("Jordi Alba", "Left Back", "")
        val user4 = User("Gerard Pique", "Center Back", "")
        val user5 = User("Clement Lenglet", "Center Back", "")
        val user6 = User("Wayne Rooney", "Center Forward", "")
        val user7 = User("Cristiano Ronaldo", "Center Forward", "")
        val user8 = User("Thiago Silva", "Center Back", "")
        val user9 = User("Neymer Jr.", "Center Forward", "")
        val user10 = User("Kylene Mbappe", "Left Winger", "")
        val user11 = User("De Gea", "Goal Keeper", "")
        val user12 = User("De Bruyne", "Midfielder", "")

        val userList = ArrayList<User>()
        userList.add(user1)
        userList.add(user2)
        userList.add(user3)
        userList.add(user4)
        userList.add(user5)
        userList.add(user6)
        userList.add(user7)
        userList.add(user8)
        userList.add(user9)
        userList.add(user10)
        userList.add(user11)
        userList.add(user12)
        return userList
    }

    fun addButtonClicked() {
        if(originalUserList.isNotEmpty()){
            userList.add(originalUserList.removeFirst())
            userLiveData.value = userList
        }
    }
}