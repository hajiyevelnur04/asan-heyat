package com.eebros.asan

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.eebros.asan.base.BaseActivity
import com.eebros.asan.di.ViewModelProviderFactory
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //init view model with factory
        viewModel = ViewModelProvider(this, factory)[MainActivityViewModel::class.java]
    }
}
