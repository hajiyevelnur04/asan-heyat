package com.eebros.asan.ui.activity.service

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eebros.asan.R
import kotlinx.android.synthetic.main.activity_service.*

class ServiceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        container1.setOnClickListener{
            startActivity(Intent(this, SelectedServiceActivity::class.java))
            this.overridePendingTransition(R.anim.enter, R.anim.exit)
        }
    }
}
