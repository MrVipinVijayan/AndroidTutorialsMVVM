package com.coderzheaven.tutorialprojects.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.coderzheaven.tutorialprojects.constants.Constants.Companion.UNKNOWN_ERROR
import com.coderzheaven.tutorialprojects.models.AppServiceResponse
import com.coderzheaven.tutorialprojects.models.User
import com.coderzheaven.tutorialprojects.repo.user_repo.UserRepoHelper
import kotlinx.coroutines.async

class UsersViewModel(application: Application, private val userRepoHelper: UserRepoHelper) :
    AndroidViewModel(application) {

    var apiServiceResponse = MutableLiveData<AppServiceResponse<List<User>>>()

    init {
        fetchUsers()
    }

    fun fetchUsers() {
        apiServiceResponse.postValue(AppServiceResponse.error(null, null))
        viewModelScope.async {
            apiServiceResponse.postValue(AppServiceResponse.loading())
            try {
                val list = userRepoHelper.getAllUsers()
                apiServiceResponse.postValue(AppServiceResponse.success(list))
            } catch (e: Exception) {
                apiServiceResponse.postValue(AppServiceResponse.error(UNKNOWN_ERROR, null))
            }
        }
    }

}