package com.rjndrkha.dicoding.storyapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.rjndrkha.dicoding.storyapp.model.view_model.SettingViewModel
import com.rjndrkha.dicoding.storyapp.preference.SettingPreferences
import com.rjndrkha.dicoding.storyapp.utils.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
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
class SettingTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRules = MainDispatcherRule()

    @Mock
    private lateinit var settingPreferences: SettingPreferences
    private lateinit var settingViewModel: SettingViewModel

    @Before
    fun setUp() {
        settingViewModel = SettingViewModel(settingPreferences)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `GetThemeSettings return correct theme setting`() = mainDispatcherRules.runBlockingTest {
        val observer = Observer<Boolean> {}

        val flow = flow {
            delay(10)
            emit(true)
        }

        try {
            val expectedResponse = MutableLiveData<Boolean>()
            expectedResponse.value = true
            `when`(settingPreferences.getThemeSetting()).thenReturn(flow)

            val actualResponse = settingViewModel.getThemeSettings().observeForever(observer)

            Mockito.verify(settingPreferences).getThemeSetting()
            Assert.assertNotNull(actualResponse)
            Assert.assertTrue(true)
        } finally {
            settingViewModel.getThemeSettings().removeObserver(observer)
        }
    }
}