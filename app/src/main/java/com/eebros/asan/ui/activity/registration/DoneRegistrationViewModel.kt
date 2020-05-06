package com.eebros.asan.ui.activity.registration

import android.content.SharedPreferences
import com.eebros.asan.base.BaseViewModel
import com.eebros.asan.base.BaseViewModelInputs
import com.eebros.asan.base.BaseViewModelOutputs
import javax.inject.Inject

interface DoneRegistrationInputViewModel: BaseViewModelInputs{
    fun savePin(pin: String)
}

interface DoneRegistrationOutputViewModel: BaseViewModelOutputs{

}

class DoneRegistrationViewModel @Inject constructor(private val sharedPreferences: SharedPreferences) :
    BaseViewModel(), DoneRegistrationInputViewModel, DoneRegistrationOutputViewModel {

    override val inputs: DoneRegistrationInputViewModel = this
    override val outputs: DoneRegistrationOutputViewModel = this

    //inputs
    override fun savePin(pin: String) {
        sharedPreferences.edit().putBoolean("pinIsSet", true).commit()
        sharedPreferences.edit().putString("loginPin", pin).apply()
    }

}
