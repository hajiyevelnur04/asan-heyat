package com.eebros.asan.ui.activity.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.eebros.asan.R
import com.eebros.asan.base.BaseActivity
import com.eebros.asan.di.ViewModelProviderFactory
import com.eebros.asan.ui.activity.main.MainActivity
import kotlinx.android.synthetic.main.activity_done_registration.*
import javax.inject.Inject

class DoneRegistrationActivity : BaseActivity() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var viewModel: DoneRegistrationViewModel

    private val pin: String by lazy{intent.getStringExtra("pin")}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_done_registration)

        viewModel = ViewModelProvider(this, factory)[DoneRegistrationViewModel::class.java]

        doneButton.setOnClickListener{
            viewModel.inputs.savePin(pin)
            startActivity(Intent(this@DoneRegistrationActivity, MainActivity::class.java))
            overridePendingTransition(R.anim.enter, R.anim.exit)
        }

    }
}
