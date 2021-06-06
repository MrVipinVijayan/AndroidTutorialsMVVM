package com.coderzheaven.tutorialprojects.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.coderzheaven.tutorialprojects.callback.UsersCallback
import com.coderzheaven.tutorialprojects.models.User
import com.coderzheaven.tutorialprojects.models.UserError
import com.coderzheaven.tutorialprojects.repo.UsersRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UsersViewModel(application: Application) : AndroidViewModel(application), UsersCallback {

    var userList: MutableLiveData<List<User>> = MutableLiveData()
    var userError: MutableLiveData<UserError> = MutableLiveData()
    var userLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun getUsers() {
        if (userLoading.value == true) {
            return
        }
        viewModelScope.launch(Dispatchers.Default) {
            UsersRepo(this@UsersViewModel).getAllUsers()
        }
    }

    override fun onSuccess(usersList: List<User>) {
        userList.postValue(usersList)
    }

    override fun onFailed(error: UserError) {
        userError.postValue(error)
    }

    override fun onLoading(loading: Boolean) {
        userLoading.postValue(loading)
    }

}