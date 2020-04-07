package com.eebros.asan.ui.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.eebros.asan.R
import kotlinx.android.synthetic.main.asan_into_header.*

class VerifyPhoneNumberActivity : AppCompatActivity() {

    lateinit var sliderDotspanel: LinearLayout

    private var dotscount = 7

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_phone_number)

        initIndigator()

        leftContainer.setOnClickListener{onBackPressed()}
    }

    private fun initIndigator() {
        sliderDotspanel = findViewById(R.id.slider_dots)
        val dots = arrayOfNulls<ImageView>(dotscount)

        for (i in 0 until dotscount) {
            dots[i] = ImageView(this)
            dots[i]!!.setImageDrawable(ContextCompat.getDrawable(this,
                R.drawable.default_dot
            ))
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(8, 0, 8, 0)
            sliderDotspanel!!.addView(dots[i], params)
        }
        dots[0]?.setImageDrawable(ContextCompat.getDrawable(this,
            R.drawable.selected_dot
        ))
        dots[1]?.setImageDrawable(ContextCompat.getDrawable(this,
            R.drawable.selected_dot
        ))
    }
}
