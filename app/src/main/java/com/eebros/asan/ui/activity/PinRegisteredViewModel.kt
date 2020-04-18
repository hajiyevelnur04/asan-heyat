package com.eebros.asan.ui.activity

import android.content.SharedPreferences
import com.eebros.asan.base.BaseViewModel
import com.eebros.asan.base.BaseViewModelInputs
import com.eebros.asan.base.BaseViewModelOutputs
import com.eebros.asan.data.remote.response.TestResponseModel
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

interface PinRegisteredViewModelInputs : BaseViewModelInputs {
    fun checkPin(pin: String)
    fun fingerprintIsSet(authenticated: Boolean)
}

interface PinRegisteredViewModelOutputs : BaseViewModelOutputs {
    fun onPinIsCorrect(): Observable<Boolean>
    fun onFingerprintIsSet(): Observable<Boolean>
}

class PinRegisteredViewModel @Inject constructor(private val sharedPreferences: SharedPreferences)
    : BaseViewModel(), PinRegisteredViewModelInputs, PinRegisteredViewModelOutputs {

    override val inputs: PinRegisteredViewModelInputs = this
    override val outputs: PinRegisteredViewModelOutputs = this

    private var pinIsCorrect = PublishSubject.create<Boolean>()
    private var fingerprintIsSet = PublishSubject.create<Boolean>()


    private val existingPin: String? by lazy { sharedPreferences.getString("loginPin" , null) }


    override fun checkPin(pin: String) {
        if(pin == existingPin) pinIsCorrect.onNext(true)  else pinIsCorrect.onNext(false)
    }

    override fun fingerprintIsSet(authenticated: Boolean) {
        sharedPreferences.edit().putBoolean("fingerprintIsSet", authenticated).apply()
    }

    override fun onPinIsCorrect() = pinIsCorrect

    override fun onFingerprintIsSet() = fingerprintIsSet

}