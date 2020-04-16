package com.eebros.asan

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.eebros.asan.base.BaseActivity
import com.eebros.asan.data.local.ServiceImageMapper
import com.eebros.asan.di.ViewModelProviderFactory
import com.eebros.asan.local.ErrorMapper
import com.eebros.asan.ui.activity.PinRegisteredActivity
import com.eebros.asan.ui.activity.PinRegistrationActivity
import com.eebros.asan.ui.activity.registration.NumberActivity
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class SplashActivity : BaseActivity() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var viewModel: SplashActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        ErrorMapper().saveDataToMap()
        ServiceImageMapper().saveCategoryMapping()

        viewModel = ViewModelProvider(this, factory)[SplashActivityViewModel::class.java]

        setOutputListener()
        setInputListener()
    }

    private fun setInputListener() {
        if (viewModel.pinIsSet) {
            startActivity(Intent(this@SplashActivity, PinRegisteredActivity::class.java))
            finish()
            //viewModel.inputs.checkToken()
        } else {
            startActivity(Intent(this@SplashActivity, NumberActivity::class.java))
            finish()
        }
    }

    private fun setOutputListener() {
        /*viewModel.outputs.tokenUpdated().subscribe {
            startActivity(Intent(this@SplashActivity, NumberActivity::class.java))
            finish()
        }.addTo(subscriptions)
        viewModel.outputs.onError().subscribe {
            val intent = Intent(this, NumberActivity::class.java)
            intent.putExtra("description", getString(ErrorMapper().getErrorMessage(it)))
            startActivity(intent)
            finish()
        }.addTo(subscriptions)*/
    }
}
