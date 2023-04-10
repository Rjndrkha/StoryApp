package com.rjndrkha.dicoding.storyapp.ui.detail_story

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.rjndrkha.dicoding.storyapp.R
import com.rjndrkha.dicoding.storyapp.custom_view.Constants
import com.rjndrkha.dicoding.storyapp.databinding.ActivityDetailStoryBinding
import com.rjndrkha.dicoding.storyapp.model.Story
import com.rjndrkha.dicoding.storyapp.utils.withDateFormat

class DetailStoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailStoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailStory = intent.getParcelableExtra<Story>(Constants.DETAIL_STORY) as Story

        setupToolBar()
        setupUi(detailStory)
    }

    private fun setupToolBar() {
        title = resources.getString(R.string.detail_story)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }

    private fun setupUi(detailStory: Story) {
        Glide.with(this@DetailStoryActivity)
            .load(detailStory.photoUrl)
            .fitCenter()
            .into(binding.storyImageView)

        detailStory.apply {
            binding.nameTextView.text = name
            binding.descriptionTextView.text = description
            binding.dateTextView.text = createdAt.withDateFormat()
        }
    }
}