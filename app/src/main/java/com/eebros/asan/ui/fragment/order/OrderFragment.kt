package com.eebros.asan.ui.fragment.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.eebros.asan.R
import com.eebros.asan.base.BaseFragment
import com.eebros.asan.di.ViewModelProviderFactory
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import javax.inject.Inject

class OrderFragment : BaseFragment() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var orderViewModel: OrderViewModel

    lateinit var viewPager: ViewPager
    lateinit var tabs: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        orderViewModel = ViewModelProvider(this, factory)[OrderViewModel::class.java]

        viewPager = root.findViewById(R.id.view_pager)
        tabs = root.findViewById(R.id.tabs)

        val adapter = OrderPageAdapter(arrayListOf(
            RidesFragment(),
            RidesFragment(),
            RidesFragment()
        ),
            childFragmentManager,
            requireContext())

        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 3

        tabs.setupWithViewPager(viewPager)

        tabs.getTabAt(0)

        viewPager.addOnPageChangeListener(
            TabLayoutOnPageChangeListener(tabs)
        )

        return root
    }
}
