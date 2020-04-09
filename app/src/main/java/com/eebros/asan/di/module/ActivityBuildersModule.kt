package com.eebros.asan.di.module

import com.eebros.asan.ui.activity.registration.RegisterNumberActivity
import com.eebros.asan.SplashActivity
import com.eebros.asan.di.module.sub.MainModule
import com.eebros.asan.di.module.viewmodel.MainModuleViewModel
import com.eebros.asan.di.scope.MainScope
import com.eebros.asan.ui.activity.EnterPinActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @MainScope
    @ContributesAndroidInjector(modules = [MainModule::class, MainModuleViewModel::class])
    abstract fun mainActivity(): RegisterNumberActivity

    @MainScope
    @ContributesAndroidInjector(modules = [MainModule::class, MainModuleViewModel::class])
    abstract fun pinActivity(): EnterPinActivity

    @MainScope
    @ContributesAndroidInjector(modules = [MainModule::class, MainModuleViewModel::class])
    abstract fun splashActivity(): SplashActivity



}