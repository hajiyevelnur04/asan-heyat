package com.eebros.asan

import android.content.SharedPreferences
import com.eebros.asan.base.BaseViewModel
import com.eebros.asan.base.BaseViewModelInputs
import com.eebros.asan.base.BaseViewModelOutputs
import io.reactivex.subjects.CompletableSubject
import javax.inject.Inject


interface SplashViewModelInputs : BaseViewModelInputs {
    fun checkToken()
}

interface SplashViewModelOutputs : BaseViewModelOutputs {
    fun tokenUpdated(): CompletableSubject
}

class SplashActivityViewModel @Inject constructor(private val sharedPrefs: SharedPreferences):
    BaseViewModel(), SplashViewModelInputs, SplashViewModelOutputs {

    override val inputs: SplashViewModelInputs = this
    override val outputs: SplashViewModelOutputs = this

    private val tokenUpdated = CompletableSubject.create()

    val pinIsSet =  sharedPrefs.getBoolean("pinIsSet", false)

    val token = sharedPrefs.getString("token", "")


    override fun checkToken() {
        //call usecase and initialize it
        /*if (it.status.statusCode == 1) {
            updateToken(it.customer.token)
        } else {
            sharedPrefs.edit().putBoolean("pinIsSet", false).apply()
            error.onNext(it.status.statusCode)
        }
    }, {
        sharedPrefs.edit().putBoolean("pinIsSet", false).apply()
        error.onNext(1878)
        it.printStackTrace()*/
        updateToken("")
    }

    private fun updateToken(token: String) {
        sharedPrefs.edit().putString("token", token).apply()
        tokenUpdated.onComplete()
    }

    override fun tokenUpdated() = tokenUpdated

}
