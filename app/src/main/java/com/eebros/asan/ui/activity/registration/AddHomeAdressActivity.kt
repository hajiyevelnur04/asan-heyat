package com.eebros.asan.ui.activity.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.eebros.asan.Constants
import com.eebros.asan.Constants.Companion.INTRO_DOTS
import com.eebros.asan.R
import kotlinx.android.synthetic.main.activity_add_home_adress.*
import kotlinx.android.synthetic.main.asan_into_header.*

class AddHomeAdressActivity : AppCompatActivity() {

    lateinit var sliderDotspanel: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_home_adress)

        initIndigator()
        leftContainer.setOnClickListener{onBackPressed()}

        continueButton.setOnClickListener{
            startActivity(Intent(this@AddHomeAdressActivity, AddEmailActivity::class.java))
            overridePendingTransition(R.anim.enter, R.anim.exit)
        }
    }

    private fun setUpSpinner() {
        val country: List<String> = arrayListOf(
            "Azerbaijan", "Turkey", "Germany", "Russia"
        )

    }

    private fun initIndigator() {
        sliderDotspanel = findViewById(R.id.slider_dots)
        val dots = arrayOfNulls<ImageView>(INTRO_DOTS)

        for (i in 0 until INTRO_DOTS) {
            dots[i] = ImageView(this)
            dots[i]!!.setImageDrawable(
                ContextCompat.getDrawable(this,
                    R.drawable.default_dot
                ))
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(8, 0, 8, 0)
            sliderDotspanel!!.addView(dots[i], params)
        }

        for(i in 0..4){
            dots[i]?.setImageDrawable(
                ContextCompat.getDrawable(this,
                    R.drawable.selected_dot
                ))
        }

    }
}
