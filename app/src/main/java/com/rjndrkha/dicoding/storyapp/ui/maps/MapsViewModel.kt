package com.rjndrkha.dicoding.storyapp.ui.maps

import androidx.lifecycle.ViewModel
import com.rjndrkha.dicoding.storyapp.data_page.StoryRepository

class MapViewModel(private val storyRepository: StoryRepository) : ViewModel() {
    fun getStories() = storyRepository.getStories()
}