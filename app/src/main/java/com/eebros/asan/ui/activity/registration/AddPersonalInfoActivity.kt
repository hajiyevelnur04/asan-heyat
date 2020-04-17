package com.eebros.asan.ui.activity.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.eebros.asan.Constants
import com.eebros.asan.Constants.Companion.INTRO_DOTS
import com.eebros.asan.R
import kotlinx.android.synthetic.main.asan_into_header.*

class AddPersonalInfoActivity : AppCompatActivity() {

    lateinit var sliderDotspanel: LinearLayout

    lateinit var continueButton: Button
    lateinit var firstNameText: EditText
    lateinit var lastNameText: EditText
    lateinit var dateOfBirthText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_personal_info)

        initView()
        initIndigator()
        leftContainer.setOnClickListener{onBackPressed()}

        continueButton.setOnClickListener{
            if(checkValidation()){
                startActivity(Intent(this@AddPersonalInfoActivity, AddHomeAdressActivity::class.java))
                overridePendingTransition(R.anim.enter, R.anim.exit)
            } else{
                //do something
            }
        }
    }

    private fun checkValidation(): Boolean{
        return if(firstNameText.text.trim().toString().isEmpty()){
            firstNameText.error = getString(R.string.pleaseAdd)
            false
        } else {
            firstNameText.error = null
            true
        }

        return if(lastNameText.text.trim().toString().isEmpty()){
            lastNameText.error = getString(R.string.pleaseAdd)
            false
        } else {
            lastNameText.error = null
            true
        }

        return if(dateOfBirthText.text.trim().toString().isEmpty()){
            dateOfBirthText.error = getString(R.string.pleaseAdd)
            false
        } else {
            dateOfBirthText.error = null
            true
        }
    }

    private fun initView() {
        continueButton = findViewById(R.id.continueButton)
        firstNameText = findViewById(R.id.firstName)
        lastNameText = findViewById(R.id.lastName)
        dateOfBirthText = findViewById(R.id.dateOfBirth)
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

        for(i in 0..3){
            dots[i]?.setImageDrawable(
                ContextCompat.getDrawable(this,
                    R.drawable.selected_dot
                ))
        }

    }
}
