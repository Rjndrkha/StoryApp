package com.rjndrkha.dicoding.storyapp.custom_view

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

import com.rjndrkha.dicoding.storyapp.R


import androidx.appcompat.app.AppCompatActivity
import com.rjndrkha.dicoding.storyapp.ui.main.MainActivity

class CustomAlertDialog(context: Context, private val message: Int, private val image: Int): AlertDialog(context) {
    init {
        setCancelable(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.error_layout)

        val messageView = findViewById<TextView>(R.id.tvError)
        messageView.text = context.getString(message)

        val imageView = findViewById<ImageView>(R.id.ivEmptyUser)
        imageView.setImageResource(image)

        val dismissButton = findViewById<Button>(R.id.dismissButton)

        dismissButton.setOnClickListener {
            // Menutup dialog
            dismiss()

            // Membuka halaman Main
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }

    }
}



