package com.eebros.asan.ui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.lifecycle.ViewModelProvider
import com.eebros.asan.R
import com.eebros.asan.base.BaseActivity
import com.eebros.asan.di.ViewModelProviderFactory
import com.eebros.asan.ui.activity.common.NumberKeyboard
import com.eebros.asan.ui.activity.common.NumberKeyboardListener
import com.eebros.asan.ui.activity.main.MainActivity
import com.eebros.asan.view.AsanNumberBoard
import com.eebros.asan.view.AsanPinView
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.number_board.*
import javax.inject.Inject


class PinRegisteredActivity : BaseActivity(), NumberKeyboardListener {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var viewModel: PinRegisteredViewModel

    //lateinit var pinKeyBoard: AsanNumberBoard
    lateinit var pinKeyBoard: NumberKeyboard
    lateinit var pin: AsanPinView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pin_registered_activity)

        viewModel = ViewModelProvider(this, factory)[PinRegisteredViewModel::class.java]

        initView()

        buttonCancel.setOnClickListener { finish() }
        buttonDelete.visibility = View.VISIBLE

        setOutputListener()
        setInputListener()

        /*if (creatingNewPin) {
            disablePinCancelling()
            showBiometricPrompt(true)
        } else {
            //viewModel.inputs.checkIfFingerPrintIsSet()
        }
*/
        pin.setTextIsSelectable(true)


        val ic: InputConnection = pin.onCreateInputConnection(EditorInfo())
        pinKeyBoard.setInputConnection(ic)

        pin.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().length == 4) {
                    viewModel.inputs.checkPin(s.toString())
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    private fun setInputListener() {
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
                navigateTo(MainActivity())
                finish()
            } else {
                Toast.makeText(this, R.string.pin_is_wrong, Toast.LENGTH_SHORT).show()
                //showDialog(getString(R.string.pin_is_wrong))
                val v = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
                } else {
                    //deprecated in API 26
                    v.vibrate(500)
                }
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
       /* viewModel.inputs.login()*/
        val intent = Intent(this, activity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun initView() {
        pinKeyBoard = findViewById(R.id.pinKeyBoard)
        pin = findViewById(R.id.pin)
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
                            /*if (!updatingCurrentPin) {
                                if (!creatingNewPin) {
                                    navigateTo(VerifyPhoneNumberActivity())
                                } else {
                                    //viewModel.inputs.fingerprintIsSet(true)
                                }
                            }*/
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
