package com.coderzheaven.tutorialprojects.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.coderzheaven.tutorialprojects.models.User
import com.coderzheaven.tutorialprojects.models.UserError
import com.coderzheaven.tutorialprojects.repo.user_repo.UserRepoHelper
import com.coderzheaven.tutorialprojects.models.AppServiceResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class UsersViewModel(application: Application, private val userRepoHelper: UserRepoHelper) :
    AndroidViewModel(application) {

    var apiServiceResponse = MutableLiveData<AppServiceResponse<List<User>>>()

    init {
        fetchUsers()
    }

    fun fetchUsers() {
        viewModelScope.launch {
            apiServiceResponse.postValue(AppServiceResponse.loading())
            try {
                val list = userRepoHelper.getAllUsers()
                apiServiceResponse.postValue(AppServiceResponse.success(list))
            } catch (e: Exception) {
                apiServiceResponse.postValue(AppServiceResponse.error("Error Occurred", null))
            }
        }
    }

}