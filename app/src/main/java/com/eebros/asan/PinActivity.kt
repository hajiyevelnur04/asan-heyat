package com.eebros.asan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import android.widget.Toast
import com.eebros.asan.view.AsanNumberBoard
import com.eebros.asan.view.AsanPinView
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.asan_toolbar_logo.*
import kotlinx.android.synthetic.main.number_board.*

class PinActivity : AppCompatActivity() {

    lateinit var pinCode: String
    private var pinStep: Int = 1
    var creatingNewPin: Boolean = false
    var updatingCurrentPin: Boolean = true

    lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin)

        val pinKeyBoard = findViewById<AsanNumberBoard>(R.id.pinKeyBoard)
        val pin = findViewById<AsanPinView>(R.id.pin)

        tabLayout = findViewById(R.id.tab_layout)
        tabLayout.setupWithViewPager(toolbar_view)

        buttonCancel.setOnClickListener {
            finish()
        }

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
                    }
                    //Enter current PIN
                } else {
                    if (s.toString().length == 4) {
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

    private fun disablePinCancelling() {
        buttonCancel.isEnabled = false
    }
}
