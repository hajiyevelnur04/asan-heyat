package com.eebros.asan.ui.activity.registration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.eebros.asan.R

class AddPersonalInfoActivity : AppCompatActivity() {

    lateinit var sliderDotspanel: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_personal_info)
    }
}
