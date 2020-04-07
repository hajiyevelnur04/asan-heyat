package com.eebros.asan.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.eebros.asan.R
import com.eebros.asan.view.AsanNumberBoard
import com.eebros.asan.view.AsanPinView
import kotlinx.android.synthetic.main.asan_into_header.*
import kotlinx.android.synthetic.main.number_board.*


class PinActivity : AppCompatActivity() {

    lateinit var pinCode: String
    private var pinStep: Int = 1
    var creatingNewPin: Boolean = true
    var updatingCurrentPin: Boolean = true

    lateinit var sliderDotspanel: LinearLayout

    private var dotscount = 7

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin)

        val pinKeyBoard = findViewById<AsanNumberBoard>(R.id.pinKeyBoard)
        val pin = findViewById<AsanPinView>(R.id.pin)

        initIndigator()

        buttonCancel.setOnClickListener {
            finish()
        }

        leftContainer.setOnClickListener{onBackPressed()}

        pin.setTextIsSelectable(true)

        val ic: InputConnection = pin.onCreateInputConnection(EditorInfo())
        pinKeyBoard.setInputConnection(ic)

        pin.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (creatingNewPin) {
                    if (s.toString().length == 4) {
                        if (pinStep == 1) {
                            pinCode = s.toString()
                            pin.setText("")
                            pinStep = 2
                            Toast.makeText(
                                this@PinActivity,
                                getString(R.string.repeat_pin),
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            if (s.toString() == pinCode) {
                                val intent = Intent(this@PinActivity, VerifyPhoneNumberActivity::class.java)
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                startActivity(intent)
                                //viewmodel.inputs.savePin(pinCode)
                            } else {
                                //showDialog(getString(R.string.repeat_pin_is_wrong))
                                pinStep = 1
                                pinCode = ""
                                pin.setText("")
                            }
                        }
                    }

                } else if (updatingCurrentPin) {
                    if (s.toString().length == 4) {
                        pin.setText("")
                        /*if (s.toString() == viewmodel.getCurrentPin()) {
                            Toast.makeText(
                                this@LoginPinActivity,
                                getString(R.string.enter_new_pin),
                                Toast.LENGTH_SHORT
                            ).show()
                            creatingNewPin = true
                        }*/
                        creatingNewPin = true
                    }
                    //Enter current PIN
                } else {
                    if (s.toString().length == 4) {
                        startActivity(Intent(this@PinActivity, VerifyPhoneNumberActivity::class.java))
                        //viewmodel.inputs.checkPin(s.toString())
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
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
    }

    private fun disablePinCancelling() {
        buttonCancel.isEnabled = false
    }
}
