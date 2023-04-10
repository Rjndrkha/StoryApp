package com.rjndrkha.dicoding.storyapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.rjndrkha.dicoding.storyapp.data_page.StoryRepository
import com.rjndrkha.dicoding.storyapp.data_page.Result
import com.rjndrkha.dicoding.storyapp.model.LoginResponse
import com.rjndrkha.dicoding.storyapp.ui.login.LoginViewModel
import com.rjndrkha.dicoding.storyapp.utils.DataDummy
import com.rjndrkha.dicoding.storyapp.utils.getOrAwaitValue
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginTest{
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var storyRepository: StoryRepository
    private lateinit var loginViewModel: LoginViewModel
    private var dummyLoginResponse = DataDummy.generateDummyLoginSuccess()
    private val dummyEmail = "email"
    private val dummyPassword = "password"

    @Before
    fun setUp() {
        loginViewModel = LoginViewModel(storyRepository)
    }

    @Test
    fun `Post Login Not Null & Return Success`() {
        val expectedLogin = MutableLiveData<Result<LoginResponse>>()
        expectedLogin.value = Result.Success(dummyLoginResponse)
        `when`(storyRepository.login(dummyEmail, dummyPassword)).thenReturn(expectedLogin)

        val actualResponse = loginViewModel.postLogin(dummyEmail, dummyPassword).getOrAwaitValue()

        Mockito.verify(storyRepository).login(dummyEmail, dummyPassword)
        Assert.assertNotNull(actualResponse)
        Assert.assertTrue(actualResponse is Result.Success)
    }

    @Test
    fun `Post Login Null & Return Error`() {
        dummyLoginResponse = DataDummy.generateDummyLoginError()

        val expectedLogin = MutableLiveData<Result<LoginResponse>>()
        expectedLogin.value = Result.Error("invalid password")
        `when`(storyRepository.login(dummyEmail, dummyPassword)).thenReturn(expectedLogin)

        val actualResponse = loginViewModel.postLogin(dummyEmail, dummyPassword).getOrAwaitValue()

        Mockito.verify(storyRepository).login(dummyEmail, dummyPassword)
        Assert.assertNotNull(actualResponse)
        Assert.assertTrue(actualResponse is Result.Error)
    }
}