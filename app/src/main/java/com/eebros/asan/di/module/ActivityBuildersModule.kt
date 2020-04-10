package com.eebros.asan.di.module

import com.eebros.asan.ui.activity.registration.RegisterNumberActivity
import com.eebros.asan.SplashActivity
import com.eebros.asan.di.module.sub.MainModule
import com.eebros.asan.di.module.viewmodel.MainModuleViewModel
import com.eebros.asan.di.scope.MainScope
import com.eebros.asan.ui.activity.EnterPinActivity
import com.eebros.asan.ui.fragment.dashboard.DashboardFragment
import com.eebros.asan.ui.fragment.home.HomeFragment
import com.eebros.asan.ui.fragment.notifications.NotificationsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @MainScope
    @ContributesAndroidInjector(modules = [MainModule::class, MainModuleViewModel::class])
    abstract fun registerNumberActivity(): RegisterNumberActivity

    @MainScope
    @ContributesAndroidInjector(modules = [MainModule::class, MainModuleViewModel::class])
    abstract fun enterPinActivity(): EnterPinActivity

    @MainScope
    @ContributesAndroidInjector(modules = [MainModule::class, MainModuleViewModel::class])
    abstract fun splashActivity(): SplashActivity


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