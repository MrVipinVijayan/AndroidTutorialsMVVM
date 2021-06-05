package com.coderzheaven.tutorialprojects.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.coderzheaven.tutorialprojects.callback.UsersCallback
import com.coderzheaven.tutorialprojects.models.User
import com.coderzheaven.tutorialprojects.repo.UsersRepo

class UsersViewModel(application: Application) : AndroidViewModel(application), UsersCallback {

    var users: MutableLiveData<List<User>> = MutableLiveData()
    var loading: MutableLiveData<Boolean> = MutableLiveData()

    fun getUsers() {
        loading.postValue(true)
        UsersRepo(this).getAllUsers()
    }

    override fun onSuccess(usersList: List<User>) {
        users.postValue(usersList)
        loading.postValue(false)
    }

    override fun onFailed() {
        loading.postValue(false)
    }

}