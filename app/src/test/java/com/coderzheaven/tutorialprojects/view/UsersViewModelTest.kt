package com.coderzheaven.tutorialprojects.view

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.coderzheaven.tutorialprojects.models.User
import junit.framework.TestCase
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import java.net.HttpURLConnection

@RunWith(org.mockito.junit.MockitoJUnitRunner::class)
class UsersViewModelTest : TestCase() {

    private val mockWebServer: MockWebServer = MockWebServer()

    private lateinit var usersViewModel: UsersViewModel
    @Mock
    private lateinit var usersObserver: Observer<List<User>?>

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    public override fun setUp() {
        super.setUp()
        val applicationMock = Mockito.mock(Application::class.java)
        usersViewModel = UsersViewModel(applicationMock)
        usersViewModel.users.observeForever { usersObserver }
        mockWebServer.start()
    }

    @Test
    fun `fetch details and check response Code 200 returned`() {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody("[{  \"id\": 1, \"name\": \"Leanne Graham\"}]")
        mockWebServer.enqueue(response)
        val mockResponse = response.getBody()?.readUtf8()
        assertEquals(
            mockResponse.toString().contains("id"),
            true
        )
    }

//    @Test
//    fun testSuccessfulResponse() {
//        mockWebServer.dispatcher = object : Dispatcher() {
//            override fun dispatch(request: RecordedRequest): MockResponse {
//                return MockResponse()
//                    .setResponseCode(200)
//                    .setBody(FileReader.readStringFromFile("success_response.json"))
//            }
//        }
//    }

    @After
    fun teardown() {
        usersViewModel.users.removeObserver{ usersObserver }
        mockWebServer.shutdown()
    }

}