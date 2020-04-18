package com.eebros.asan.ui.fragment.order

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.eebros.asan.R
import com.eebros.asan.base.BaseFragment

private val TAB_TITLES = arrayOf(
    R.string.rides,
    R.string.deliveries,
    R.string.on_demand
)

class OrderPageAdapter(private val pages: ArrayList<BaseFragment>, fm: FragmentManager,
                       private val context: Context) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int) = pages[position]

    override fun getPageTitle(position: Int) = context.getString(TAB_TITLES[position])

    override fun getCount() = pages.size
}