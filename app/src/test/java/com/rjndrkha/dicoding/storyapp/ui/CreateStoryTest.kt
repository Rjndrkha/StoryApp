package com.rjndrkha.dicoding.storyapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.rjndrkha.dicoding.storyapp.data_page.StoryRepository
import com.rjndrkha.dicoding.storyapp.data_page.Result
import com.rjndrkha.dicoding.storyapp.model.CreateStoryResponse
import com.rjndrkha.dicoding.storyapp.ui.create_story.CreateStoryViewModel
import com.rjndrkha.dicoding.storyapp.utils.DataDummy
import com.rjndrkha.dicoding.storyapp.utils.getOrAwaitValue
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import java.io.File

@RunWith(MockitoJUnitRunner::class)
class CreateStoryTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var storyRepository: StoryRepository
    private lateinit var createStoryViewModel: CreateStoryViewModel
    private var dummyResponse = DataDummy.generateDummyCreateStorySuccess()
    private var dummyDesc = "description".toRequestBody("text/plain".toMediaType())
    private var dummyLat = 0.01
    private var dummyLon = 0.01

    private val file: File = mock(File::class.java)
    private val requestImageFile = file.asRequestBody("image/jpg".toMediaTypeOrNull())
    private val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
        "photo",
        file.name,
        requestImageFile
    )

    @Before
    fun setUp() {
        createStoryViewModel = CreateStoryViewModel(storyRepository)
    }

    @Test
    fun `Post Create Story Not Null & Return Success`() {
        val expectedCreateStory = MutableLiveData<Result<CreateStoryResponse>>()
        expectedCreateStory.value = Result.Success(dummyResponse)
        `when`(storyRepository.createStory(
            imageFile = imageMultipart,
            desc = dummyDesc,
            lat = dummyLat,
            lon = dummyLon
        )).thenReturn(expectedCreateStory)

        val actualResponse = createStoryViewModel.postCreateStory(
            imageFile = imageMultipart,
            desc = dummyDesc,
            lat = dummyLat,
            lon = dummyLon
        ).getOrAwaitValue()

        Mockito.verify(storyRepository).createStory(
            imageFile = imageMultipart,
            desc = dummyDesc,
            lat = dummyLat,
            lon = dummyLon
        )
        Assert.assertNotNull(actualResponse)
        Assert.assertTrue(actualResponse is Result.Success)
    }

    @Test
    fun `Post Create Story Null & Return Error`() {
        dummyResponse = DataDummy.generateDummyCreateStoryError()

        val expectedCreateStory = MutableLiveData<Result<CreateStoryResponse>>()
        expectedCreateStory.value = Result.Error("error")
        `when`(storyRepository.createStory(
            imageFile = imageMultipart,
            desc = dummyDesc,
            lat = dummyLat,
            lon = dummyLon
        )).thenReturn(expectedCreateStory)

        val actualResponse = createStoryViewModel.postCreateStory(
            imageFile = imageMultipart,
            desc = dummyDesc,
            lat = dummyLat,
            lon = dummyLon
        ).getOrAwaitValue()

        Mockito.verify(storyRepository).createStory(
            imageFile = imageMultipart,
            desc = dummyDesc,
            lat = dummyLat,
            lon = dummyLon
        )
        Assert.assertNotNull(actualResponse)
        Assert.assertTrue(actualResponse is Result.Error)
    }
}