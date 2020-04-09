package com.eebros.asan.ui.activity.registration

import android.app.Activity
import android.content.Context
import com.eebros.asan.base.BaseViewModel
import com.eebros.asan.base.BaseViewModelInputs
import com.eebros.asan.base.BaseViewModelOutputs
import com.eebros.asan.remote.data.response.TestResponseModel
import io.reactivex.subjects.PublishSubject

import javax.inject.Inject

interface RegisterNumberInputs : BaseViewModelInputs{
    fun sendVerificationNumber(activity: Activity, number: String)
}

interface RegisterNumberOutputs: BaseViewModelOutputs{
    fun testOnSuccess(): PublishSubject<ArrayList<TestResponseModel>>
}

class RegisterNumberViewModel @Inject constructor(context: Context) : BaseViewModel(),
    RegisterNumberInputs,
    RegisterNumberOutputs {
    override val inputs: RegisterNumberInputs = this
    override val outputs: RegisterNumberOutputs = this

    private val testSuccess = PublishSubject.create<ArrayList<TestResponseModel>>()

    private val verifyCode: String = ""

    override fun sendVerificationNumber(activity: Activity, phoneNumber: String) {
    }

    override fun testOnSuccess() = testSuccess

}
