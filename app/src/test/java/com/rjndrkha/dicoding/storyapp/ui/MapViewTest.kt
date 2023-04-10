package com.rjndrkha.dicoding.storyapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.rjndrkha.dicoding.storyapp.data_page.StoryRepository
import com.rjndrkha.dicoding.storyapp.data_page.Result
import com.rjndrkha.dicoding.storyapp.model.StoryResponse
import com.rjndrkha.dicoding.storyapp.ui.maps.MapViewModel
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
class MapViewTest{
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var storyRepository: StoryRepository
    private lateinit var mapViewModel: MapViewModel
    private var dummyStory = DataDummy.generateDummyStory()

    @Before
    fun setUp() {
        mapViewModel = MapViewModel(storyRepository)
    }

    @Test
    fun `Get Story Not Null & Return Success`() {
        val expectedResponse = MutableLiveData<Result<StoryResponse>>()
        expectedResponse.value = Result.Success(dummyStory)
        `when`(storyRepository.getStories()).thenReturn(expectedResponse)

        val actualResponse = mapViewModel.getStories().getOrAwaitValue()

        Mockito.verify(storyRepository).getStories()
        Assert.assertNotNull(actualResponse)
        Assert.assertTrue(actualResponse is Result.Success)
    }

    @Test
    fun `Get Story Null & Return Error`() {
        dummyStory = DataDummy.generateErrorDummyStory()

        val expectedResponse = MutableLiveData<Result<StoryResponse>>()
        expectedResponse.value = Result.Error("error")
        `when`(storyRepository.getStories()).thenReturn(expectedResponse)

        val actualResponse = mapViewModel.getStories().getOrAwaitValue()

        Mockito.verify(storyRepository).getStories()
        Assert.assertNotNull(actualResponse)
        Assert.assertTrue(actualResponse is Result.Error)
    }
}