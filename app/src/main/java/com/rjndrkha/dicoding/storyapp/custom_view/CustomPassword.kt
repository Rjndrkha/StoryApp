package com.rjndrkha.dicoding.storyapp.custom_view

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText
import com.rjndrkha.dicoding.storyapp.R
import com.rjndrkha.dicoding.storyapp.utils.validateMinLength

class CustomPassword : TextInputEditText {
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }


    private fun init() {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(text: Editable?) {
                if (validateMinLength(text.toString())) {
                    this@CustomPassword.error = null
                } else {
                    this@CustomPassword.error = context.getString(R.string.invalid_password)
                }
            }
        })
    }
}