package com.rjndrkha.dicoding.storyapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.rjndrkha.dicoding.storyapp.ui.main.MainActivity
import com.rjndrkha.dicoding.storyapp.custom_view.Constants
import com.rjndrkha.dicoding.storyapp.databinding.ActivitySplashScreenBinding
import com.rjndrkha.dicoding.storyapp.model.LoginModel
import com.rjndrkha.dicoding.storyapp.preference.LoginPreference
import com.rjndrkha.dicoding.storyapp.preference.SettingPreferences
import com.rjndrkha.dicoding.storyapp.ui.login.LoginActivity
import com.rjndrkha.dicoding.storyapp.model.view_model.SettingViewModel
import com.rjndrkha.dicoding.storyapp.model.view_model.SettingViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
@SuppressLint("CustomSplashScreen")
class SplashScreenMain : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var mLoginPreference: LoginPreference
    private lateinit var loginModel: LoginModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mLoginPreference = LoginPreference(this)

        loginModel = mLoginPreference.getUser()

        themeSettingHandler()
        splashScreenHandler()
    }

    private fun splashScreenHandler() {
        if (loginModel.name != null && loginModel.userId != null && loginModel.token != null) {
            val intent = Intent(this, MainActivity::class.java)
            navigate(intent)
        } else {
            val intent = Intent(this, LoginActivity::class.java)
            navigate(intent)
        }
    }

    private fun navigate(intent: Intent) {
        val splashTimer: Long = Constants.SPLASH_SCREEN_TIMER
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(intent)
            finish()
        }, splashTimer)
    }

    private fun themeSettingHandler() {
        val pref = SettingPreferences.getInstance(dataStore)
        val settingViewModel = ViewModelProvider(this, SettingViewModelFactory(pref)).get(
            SettingViewModel::class.java
        )
        settingViewModel.getThemeSettings().observe(this
        ) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

        }
    }
}