package com.rjndrkha.dicoding.storyapp.ui.create_story

import androidx.lifecycle.ViewModel
import com.rjndrkha.dicoding.storyapp.data_page.StoryRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody

class CreateStoryViewModel(private val storyRepository: StoryRepository): ViewModel() {
    fun postCreateStory(imageFile: MultipartBody.Part, desc: RequestBody, lat: Double, lon: Double) = storyRepository.createStory(imageFile, desc, lat, lon)
}