package com.eebros.asan.ui.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.eebros.asan.R
import com.eebros.asan.base.BaseActivity
import com.eebros.asan.di.ViewModelProviderFactory
import javax.inject.Inject

class ComingSoonActivity : BaseActivity() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var viewModel: ComingSoonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comming_soon)

        viewModel = ViewModelProvider(this, factory)[ComingSoonViewModel::class.java]
    }
}
