package com.eebros.asan.ui.activity.registration

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.eebros.asan.Constants.Companion.INTRO_DOTS
import com.eebros.asan.R
import com.eebros.asan.ui.activity.common.ErrorActivity
import com.eebros.asan.view.AsanPinView
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_verify_phone_number.*
import kotlinx.android.synthetic.main.asan_into_header.*
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.concurrent.TimeUnit

class VerifyPhoneNumberActivity : AppCompatActivity() {

    lateinit var sliderDotspanel: LinearLayout

    val firebaseAuth = FirebaseAuth.getInstance()

    val TAG: String = "TAG"

    var code: String = ""

    private var storedVerificationId: String =""

    private val phoneNumber: String by lazy{intent.getStringExtra("phoneNumber")}

    lateinit var pin: AsanPinView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_phone_number)

        initIndigator()

        leftContainer.setOnClickListener{onBackPressed()}

        /*setOutputListeners()
        setInputListeners()*/

        countDown.start()

        sendVerificationCode(phoneNumber)

        pin = findViewById(R.id.pinCodeView)
        pin.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().length == 6){
                    countDown.cancel()
                    startActivity(Intent(this@VerifyPhoneNumberActivity, AddPersonalInfoActivity::class.java))
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

    }

    private fun blockUser() {
        //viewModel.inputs.blockUser(custId, compId)
    }

    private fun sendVerificationCode(phoneNumber: String) {
        val phoneAuthProvider = PhoneAuthProvider.getInstance()
        phoneAuthProvider.verifyPhoneNumber(
            phoneNumber,
            60,
            TimeUnit.SECONDS,
            this,
            object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    Log.d(TAG, "onVerificationCompleted:$credential")
                    code = credential.smsCode.toString()
                    if(code.isNotEmpty()){
                        pinCodeView.setText(code)
                        verifyVerificationCode(code)
                    }
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    Log.w(TAG, "onVerificationFailed", e)
                }

                override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                    storedVerificationId = verificationId
                }
            })
    }

    private fun verifyVerificationCode(code: String) {
        val credential = PhoneAuthProvider.getCredential(storedVerificationId, code)
        signInWithPhoneAuthCredential(credential)
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
        for(i in 0..1){
            dots[i]?.setImageDrawable(
                ContextCompat.getDrawable(this,
                    R.drawable.selected_dot
                ))
        }
    }


    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")

                    val user = task.result?.user
                    // ...
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                }
            }

    }

    private var counter = 0
    private val decimalFormat = DecimalFormat("00")
    private val countDown = object : CountDownTimer(90000, 1000) {

        override fun onTick(millisUntilFinished: Long) {
            decimalFormat.roundingMode = RoundingMode.CEILING
            timer.text =
                ((millisUntilFinished / 1000) / 60).toString() + ":" + decimalFormat.format((millisUntilFinished / 1000) % 60).toString()
        }

        override fun onFinish() {
            startActivity(Intent(this@VerifyPhoneNumberActivity, ErrorActivity::class.java))
            finish()
        }
    }
}
