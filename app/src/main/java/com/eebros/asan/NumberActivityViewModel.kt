package com.eebros.asan

import android.app.Activity
import android.content.Context
import android.util.Log
import com.eebros.asan.base.BaseViewModel
import com.eebros.asan.base.BaseViewModelInputs
import com.eebros.asan.base.BaseViewModelOutputs
import com.eebros.asan.remote.data.response.TestResponseModel
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

import javax.inject.Inject

interface MainActivityInputs : BaseViewModelInputs{
    fun sendVerificationNumber(activity: Activity, number: String)
}

interface MainActivityOutputs: BaseViewModelOutputs{
    fun testOnSuccess(): PublishSubject<ArrayList<TestResponseModel>>
}

class MainActivityViewModel @Inject constructor(context: Context) : BaseViewModel(), MainActivityInputs, MainActivityOutputs {
    override val inputs: MainActivityInputs = this
    override val outputs: MainActivityOutputs = this

    private val testSuccess = PublishSubject.create<ArrayList<TestResponseModel>>()

    private val verifyCode: String = ""

    override fun sendVerificationNumber(activity: Activity, phoneNumber: String) {
    }

    override fun testOnSuccess() = testSuccess

}
