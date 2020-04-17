package com.eebros.asan.di.module

import com.eebros.asan.SplashActivity
import com.eebros.asan.di.module.sub.MainModule
import com.eebros.asan.di.module.viewmodel.MainModuleViewModel
import com.eebros.asan.di.scope.MainScope
import com.eebros.asan.ui.activity.PinRegisteredActivity
import com.eebros.asan.ui.activity.PinRegistrationActivity
import com.eebros.asan.ui.activity.driver.MapsActivity
import com.eebros.asan.ui.activity.main.MainActivity
import com.eebros.asan.ui.activity.registration.DoneRegistrationActivity
import com.eebros.asan.ui.activity.registration.NumberActivity
import com.eebros.asan.ui.fragment.dashboard.DashboardFragment
import com.eebros.asan.ui.fragment.home.HomeFragment
import com.eebros.asan.ui.fragment.notifications.NotificationsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @MainScope
    @ContributesAndroidInjector(modules = [MainModule::class, MainModuleViewModel::class])
    abstract fun numberActivity(): NumberActivity

    @MainScope
    @ContributesAndroidInjector(modules = [MainModule::class, MainModuleViewModel::class])
    abstract fun mapsActivity(): MapsActivity



    @MainScope
    @ContributesAndroidInjector(modules = [MainModule::class, MainModuleViewModel::class])
    abstract fun pinRegistrationActivity(): PinRegistrationActivity

    @MainScope
    @ContributesAndroidInjector(modules = [MainModule::class, MainModuleViewModel::class])
    abstract fun pinRegisteredActivity(): PinRegisteredActivity

    @MainScope
    @ContributesAndroidInjector(modules = [MainModule::class, MainModuleViewModel::class])
    abstract fun splashActivity(): SplashActivity

    @MainScope
    @ContributesAndroidInjector(modules = [MainModule::class, MainModuleViewModel::class])
    abstract fun doneRegistrationActivity(): DoneRegistrationActivity

    @MainScope
    @ContributesAndroidInjector(modules = [MainModule::class, MainModuleViewModel::class])
    abstract fun mainActivity(): MainActivity


    @MainScope
    @ContributesAndroidInjector(modules = [MainModule::class, MainModuleViewModel::class])
    abstract fun homeFragment(): HomeFragment

    @MainScope
    @ContributesAndroidInjector(modules = [MainModule::class, MainModuleViewModel::class])
    abstract fun dashboardFragment(): DashboardFragment

    @MainScope
    @ContributesAndroidInjector(modules = [MainModule::class, MainModuleViewModel::class])
    abstract fun notificationsFragment(): NotificationsFragment


}