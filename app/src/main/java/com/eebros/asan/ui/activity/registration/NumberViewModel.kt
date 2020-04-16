package com.eebros.asan.ui.activity.registration

import android.app.Activity
import android.content.Context
import com.eebros.asan.base.BaseViewModel
import com.eebros.asan.base.BaseViewModelInputs
import com.eebros.asan.base.BaseViewModelOutputs
import com.eebros.asan.data.remote.response.TestResponseModel
import io.reactivex.subjects.PublishSubject

import javax.inject.Inject

interface NumberInputs : BaseViewModelInputs{
    fun sendVerificationNumber(activity: Activity, number: String)
}

interface NumberOutputs: BaseViewModelOutputs{
    fun testOnSuccess(): PublishSubject<ArrayList<TestResponseModel>>
}

class NumberViewModel @Inject constructor(context: Context) : BaseViewModel(),
    NumberInputs,
    NumberOutputs {
    override val inputs: NumberInputs = this
    override val outputs: NumberOutputs = this

    private val testSuccess = PublishSubject.create<ArrayList<TestResponseModel>>()

    private val verifyCode: String = ""

    override fun sendVerificationNumber(activity: Activity, phoneNumber: String) {
    }

    override fun testOnSuccess() = testSuccess

}
