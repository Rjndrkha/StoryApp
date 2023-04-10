package com.rjndrkha.dicoding.storyapp.ui.login

import androidx.lifecycle.ViewModel
import com.rjndrkha.dicoding.storyapp.data_page.StoryRepository

class LoginViewModel(private val storyRepository: StoryRepository): ViewModel() {
    fun postLogin(email: String, password: String) = storyRepository.login(email, password)
}