package com.eebros.asan

import android.content.Context
import com.eebros.asan.base.BaseViewModel
import com.eebros.asan.base.BaseViewModelInputs
import com.eebros.asan.base.BaseViewModelOutputs
import com.eebros.asan.remote.data.response.TestResponseModel
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

interface MainActivityInputs : BaseViewModelInputs{
    fun test()
}

interface MainActivityOutputs: BaseViewModelOutputs{
    fun testOnSuccess(): PublishSubject<ArrayList<TestResponseModel>>
}

class MainActivityViewModel @Inject constructor(context: Context) : BaseViewModel(), MainActivityInputs, MainActivityOutputs {
    override val inputs: MainActivityInputs = this
    override val outputs: MainActivityOutputs = this

    private val testSuccess = PublishSubject.create<ArrayList<TestResponseModel>>()

    override fun test() {
        // implement testSuccess
    }

    override fun testOnSuccess() = testSuccess

}
