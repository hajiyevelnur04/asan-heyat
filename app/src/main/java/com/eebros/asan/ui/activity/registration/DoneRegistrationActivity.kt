package com.eebros.asan.ui.activity.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eebros.asan.R
import com.eebros.asan.ui.activity.main.MainActivity
import kotlinx.android.synthetic.main.activity_done_registration.*

class DoneRegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_done_registration)

        doneButton.setOnClickListener{
            startActivity(Intent(this@DoneRegistrationActivity, MainActivity::class.java))
        }

    }
}
