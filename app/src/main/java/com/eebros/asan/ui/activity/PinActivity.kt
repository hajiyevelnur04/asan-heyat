package com.eebros.asan.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.eebros.asan.Constants.Companion.INTRO_DOTS
import com.eebros.asan.R
import com.eebros.asan.base.BaseActivity
import com.eebros.asan.di.ViewModelProviderFactory
import com.eebros.asan.view.AsanNumberBoard
import com.eebros.asan.view.AsanPinView
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.asan_into_header.*
import kotlinx.android.synthetic.main.number_board.*
import javax.inject.Inject


class PinActivity : BaseActivity() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var viewModel: PinActivityViewModel

    lateinit var pinCode: String
    private var pinStep: Int = 1

    var creatingNewPin: Boolean = true
    var updatingCurrentPin: Boolean = true

    lateinit var sliderDotspanel: LinearLayout
    lateinit var pinKeyBoard: AsanNumberBoard
    lateinit var pin: AsanPinView

    private val phoneNumber: String by lazy{intent.getStringExtra("phoneNum")}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin)

        viewModel = ViewModelProvider(this, factory)[PinActivityViewModel::class.java]

        initView()
        initIndigator()

        leftContainer.setOnClickListener{onBackPressed()}

        buttonCancel.setOnClickListener { finish() }

        setOutputListener()
        setInputListener()

        if (creatingNewPin) {
            disablePinCancelling()
            showBiometricPrompt(true)
        } else {
            viewModel.inputs.checkIfFingerPrintIsSet()
        }

        pin.setTextIsSelectable(true)

        if (updatingCurrentPin) {
            Toast.makeText(
                this,
                getString(R.string.enter_current_pin),
                Toast.LENGTH_LONG
            ).show()
        } else if(creatingNewPin){
            Toast.makeText(this, getString(R.string.enter_pin), Toast.LENGTH_SHORT).show()
        }

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
                                viewModel.inputs.savePin(pinCode)
                                navigateTo(VerifyPhoneNumberActivity())
                            } else {
                                showDialog(getString(R.string.repeat_pin_is_wrong))
                                pinStep = 1
                                pinCode = ""
                                pin.setText("")
                            }
                        }
                    }

                } else if (updatingCurrentPin) {
                    if (s.toString().length == 4) {
                        pin.setText("")
                        if (s.toString() == viewModel.getCurrentPin()) {
                            Toast.makeText(
                                this@PinActivity,
                                getString(R.string.enter_new_pin),
                                Toast.LENGTH_SHORT
                            ).show()
                            creatingNewPin = true
                        }
                        creatingNewPin = true
                    }
                    //Enter current PIN
                } else {
                    if (s.toString().length == 4) {
                        viewModel.inputs.checkPin(s.toString())
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    private fun setInputListener() {
        viewModel.inputs.getUserInfo()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            pin.showSoftInputOnFocus = false
        } else {
            pin.setTextIsSelectable(false)
        }
    }

    private fun setOutputListener() {

        viewModel.outputs.onFingerprintIsSet().subscribe { isSet ->
            if (isSet)
                showBiometricPrompt(isSet)
        }.addTo(subscriptions)

        viewModel.outputs.onPinIsCorrect().subscribe { pinIsCorrect ->
            if (pinIsCorrect) {
                navigateTo(VerifyPhoneNumberActivity())
                finish()
            } else {
                showDialog(getString(R.string.pin_is_wrong))
                pin.setText("")
            }
        }.addTo(subscriptions)
    }

    private fun showDialog(message: String) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok)) { _, _ ->

            }
            .show()
    }

    private fun navigateTo(activity: Activity){
        /*if (!updatingCurrentPin) {
            viewModel.inputs.login()
            val intent = Intent(this, activity::class.java)
            intent.putExtra("phoneNumber",phoneNumber)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
        finish()*/
        viewModel.inputs.login()
        val intent = Intent(this, activity::class.java)
        intent.putExtra("phoneNumber",phoneNumber)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun initView() {
        pinKeyBoard = findViewById<AsanNumberBoard>(R.id.pinKeyBoard)
        pin = findViewById<AsanPinView>(R.id.pin)
    }

    private fun initIndigator() {
        sliderDotspanel = findViewById(R.id.slider_dots)
        val dots = arrayOfNulls<ImageView>(INTRO_DOTS)

        for (i in 0 until INTRO_DOTS) {
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

    private fun showBiometricPrompt(fingerPrintIsSet: Boolean) {
        if (BiometricManager.from(this).canAuthenticate() == BiometricManager.BIOMETRIC_SUCCESS) {
            val promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle(getString(R.string.biometric_login))
                .setSubtitle(getString(R.string.login_with_biometric_credentials))
                .setNegativeButtonText(getString(R.string.cancel_all_caps))
                .build()

            val biometricPrompt = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                BiometricPrompt(this, mainExecutor,
                    object : BiometricPrompt.AuthenticationCallback() {

                        override fun onAuthenticationError(
                            errorCode: Int,
                            errString: CharSequence
                        ) {
                            super.onAuthenticationError(errorCode, errString)
                            Toast.makeText(
                                applicationContext,
                                getString(R.string.error_message), Toast.LENGTH_SHORT
                            ).show()
                        }

                        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                            super.onAuthenticationSucceeded(result)
                            // User has verified the signature, cipher, or message
                            // authentication code (MAC) associated with the crypto object,
                            // so you can use it in your app's crypto-driven workflows.
                            if (!updatingCurrentPin) {
                                if (!creatingNewPin) {
                                    navigateTo(VerifyPhoneNumberActivity())
                                } else {
                                    viewModel.inputs.fingerprintIsSet(true)
                                }
                            }
                        }

                        override fun onAuthenticationFailed() {
                            super.onAuthenticationFailed()
                            Toast.makeText(
                                applicationContext, getString(R.string.authentication_failed),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }).authenticate(promptInfo)
            } else {

            }
        }
    }
}
