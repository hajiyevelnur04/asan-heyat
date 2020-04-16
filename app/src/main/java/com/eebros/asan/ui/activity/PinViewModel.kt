package com.eebros.asan.ui.activity

import android.content.SharedPreferences
import com.eebros.asan.base.BaseViewModel
import com.eebros.asan.base.BaseViewModelInputs
import com.eebros.asan.base.BaseViewModelOutputs
import com.eebros.asan.remote.data.response.TestResponseModel
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

interface EnterPinViewModelInputs : BaseViewModelInputs{
    fun savePin(pin: String)
    fun checkPin(pin: String)
    fun fingerprintIsSet(authenticated: Boolean)
    fun checkIfFingerPrintIsSet()
    fun login()
    fun getUserInfo()
}

interface EnterPinViewModelOutputs : BaseViewModelOutputs{
    fun onPinIsCorrect(): Observable<Boolean>
    fun onFingerprintIsSet(): Observable<Boolean>
    fun onUserName(): String?
    fun onGetUserInfoSuccess(): PublishSubject<TestResponseModel>
}

class EnterPinViewModel @Inject constructor(private val sharedPreferences: SharedPreferences) :
    BaseViewModel(), EnterPinViewModelInputs, EnterPinViewModelOutputs{

    override val inputs: EnterPinViewModelInputs = this
    override val outputs: EnterPinViewModelOutputs = this

    private var pinIsCorrect = PublishSubject.create<Boolean>()
    private var fingerprintIsSet = PublishSubject.create<Boolean>()

    private val getUserInfoSuccess = PublishSubject.create<TestResponseModel>()
    private val existingPin: String? by lazy { sharedPreferences.getString("loginPin" , null) }


    //inputs
    override fun savePin(pin: String) {
        sharedPreferences.edit().putBoolean("pinIsSet", true).apply()
        sharedPreferences.edit().putString("loginPin", pin).apply()
    }

    override fun checkPin(pin: String) {
        if(pin == existingPin) pinIsCorrect.onNext(true)  else pinIsCorrect.onNext(false)
    }

    override fun fingerprintIsSet(authenticated: Boolean) {
        sharedPreferences.edit().putBoolean("fingerprintIsSet", authenticated).apply()
    }

    override fun checkIfFingerPrintIsSet() {
        fingerprintIsSet.onNext(sharedPreferences.getBoolean("fingerprintIsSet", false))
    }

    override fun login() {
        sharedPreferences.edit().putBoolean("userLoggedIn", true).apply()
    }

    override fun getUserInfo() {
        //implement google user auth
    }

    fun getCurrentPin(): String? = existingPin


    //outputs
    override fun onPinIsCorrect() = pinIsCorrect

    override fun onFingerprintIsSet() = fingerprintIsSet

    override fun onUserName() = sharedPreferences.getString("userName", "")

    override fun onGetUserInfoSuccess() = getUserInfoSuccess

}