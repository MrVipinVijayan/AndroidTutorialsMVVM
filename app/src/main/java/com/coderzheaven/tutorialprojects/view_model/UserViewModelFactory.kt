package com.coderzheaven.tutorialprojects.view_model

import android.app.Application
import androidx.lifecycle.ViewModel

import androidx.lifecycle.ViewModelProvider
import com.coderzheaven.tutorialprojects.repo.user_repo.UserRepoHelper

class UserViewModelFactory(
    private val application: Application,
    private val userRepoHelper: UserRepoHelper
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UsersViewModel(application, userRepoHelper) as T
    }

}