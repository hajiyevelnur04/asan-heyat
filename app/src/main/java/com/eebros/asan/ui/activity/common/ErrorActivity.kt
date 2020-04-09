package com.eebros.asan.ui.activity.common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eebros.asan.R

class ErrorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error)
    }
}
