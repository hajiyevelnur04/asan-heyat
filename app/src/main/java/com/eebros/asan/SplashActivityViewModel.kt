package com.eebros.asan

import android.content.SharedPreferences
import com.eebros.asan.base.BaseViewModel
import com.eebros.asan.base.BaseViewModelInputs
import com.eebros.asan.base.BaseViewModelOutputs
import com.eebros.asan.usecases.CheckTokenUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.CompletableSubject
import javax.inject.Inject


interface SplashViewModelInputs : BaseViewModelInputs {
    fun checkToken()
}

interface SplashViewModelOutputs : BaseViewModelOutputs {
    fun tokenUpdated(): CompletableSubject
}

class SplashActivityViewModel @Inject constructor(private val sharedPrefs: SharedPreferences,
                                                  private val checkTokenUseCase: CheckTokenUseCase
):
    BaseViewModel(), SplashViewModelInputs, SplashViewModelOutputs {

    override val inputs: SplashViewModelInputs = this
    override val outputs: SplashViewModelOutputs = this

    private val tokenUpdated = CompletableSubject.create()

    val pinIsSet =  sharedPrefs.getBoolean("pinIsSet", false)

    val token = sharedPrefs.getString("token", "")
    val custId = sharedPrefs.getLong("custId", 0L)


    override fun checkToken() {
        checkTokenUseCase.execute("",custId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.status == 1) {
                    updateToken(it.token)
                } else {
                    //sharedPrefs.edit().putBoolean("pinIsSet", false).apply()
                    error.onNext(it.status)
                }
            },{
                //sharedPrefs.edit().putBoolean("pinIsSet", false).apply()
                error.onNext(1992)
                it.printStackTrace()
            }).addTo(subscriptions)

        updateToken("")
    }

    private fun updateToken(token: String) {
        sharedPrefs.edit().putString("token", token).apply()
        tokenUpdated.onComplete()
    }

    override fun tokenUpdated() = tokenUpdated

}
