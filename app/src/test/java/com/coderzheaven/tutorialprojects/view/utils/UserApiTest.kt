package com.coderzheaven.tutorialprojects.view.utils

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.coderzheaven.tutorialprojects.models.User
import com.coderzheaven.tutorialprojects.repo.user_repo.UserRepoHelper
import com.coderzheaven.tutorialprojects.models.AppServiceResponse
import com.coderzheaven.tutorialprojects.view_model.UsersViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UsersApiTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var userRepoHelper: UserRepoHelper

    @Mock
    private lateinit var apiUsersObserver: Observer<AppServiceResponse<List<User>>>

    @Before
    fun setUp() {
    }

    @Test
    fun shouldReturnSuccessOn200() {
        testCoroutineRule.runBlockingTest {
            val applicationMock = mock(Application::class.java)
            doReturn(emptyList<User>())
                .`when`(userRepoHelper)
                .getAllUsers()
            val viewModel = UsersViewModel(applicationMock, userRepoHelper)
            viewModel.apiServiceResponse.observeForever(apiUsersObserver)
            verify(userRepoHelper).getAllUsers()
            verify(apiUsersObserver).onChanged(AppServiceResponse.success(emptyList()))
            viewModel.apiServiceResponse.removeObserver(apiUsersObserver)
        }
    }

    @Test
    fun shouldReturnErrorWhenThereIsException() {
        val applicationMock = mock(Application::class.java)
        testCoroutineRule.runBlockingTest {
            val errorMessage = "Error Occurred"
            doThrow(RuntimeException(errorMessage))
                .`when`(userRepoHelper)
                .getAllUsers()
            val viewModel = UsersViewModel(applicationMock, userRepoHelper)
            viewModel.apiServiceResponse.observeForever(apiUsersObserver)
            verify(userRepoHelper).getAllUsers()
            verify(apiUsersObserver).onChanged(
                AppServiceResponse.error(errorMessage, null)
            )
            viewModel.apiServiceResponse.removeObserver(apiUsersObserver)
        }
    }


    @After
    fun tearDown() {
        // do something if required
    }
}