package com.eebros.asan.ui.fragment.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.eebros.asan.R
import com.eebros.asan.base.BaseFragment
import com.eebros.asan.di.ViewModelProviderFactory
import kotlinx.android.synthetic.main.fragment_dashboard.*
import javax.inject.Inject

class RidesFragment : BaseFragment() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var viewModel: RidesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this, factory)[RidesViewModel::class.java]
        val root = inflater.inflate(R.layout.fragment_rides, container, false)
        return root
    }
}
