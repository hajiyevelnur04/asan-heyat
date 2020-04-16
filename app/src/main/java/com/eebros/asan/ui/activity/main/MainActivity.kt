package com.eebros.asan.ui.activity.main

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.eebros.asan.R
import com.eebros.asan.base.BaseActivity
import com.eebros.asan.di.ViewModelProviderFactory
import com.eebros.asan.ui.fragment.home.HomeFragment
import com.eebros.asan.ui.fragment.dashboard.DashboardFragment
import com.eebros.asan.ui.fragment.notifications.NotificationsFragment
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        val toolbar: Toolbar = findViewById(R.id.home_screen_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val navView: BottomNavigationView = findViewById(R.id.bottomNavigationView)

        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.host_fragment, HomeFragment()).commit()

        navView.setOnNavigationItemSelectedListener { item ->
            val transaction = supportFragmentManager.beginTransaction()
            when (item.itemId) {
                R.id.navigation_home -> {
                    transaction.replace(R.id.host_fragment, HomeFragment()).commit()
                }
                R.id.navigation_dashboard -> {
                    transaction.replace(R.id.host_fragment, DashboardFragment()).commit()
                }
                R.id.navigation_notifications -> {
                    transaction.replace(R.id.host_fragment, NotificationsFragment()).commit()
                }
            }
            true
        }
    }
}
