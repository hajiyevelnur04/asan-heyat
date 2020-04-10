package com.eebros.asan.di.module.viewmodel

import androidx.lifecycle.ViewModel
import com.eebros.asan.ui.activity.registration.RegisterNumberViewModel
import com.eebros.asan.SplashActivityViewModel
import com.eebros.asan.di.scope.MainScope
import com.eebros.asan.di.scope.ViewModelKey
import com.eebros.asan.ui.activity.EnterPinViewModel
import com.eebros.asan.ui.fragment.dashboard.DashboardViewModel
import com.eebros.asan.ui.fragment.home.HomeViewModel
import com.eebros.asan.ui.fragment.notifications.NotificationsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainModuleViewModel {
    @Binds
    @MainScope
    @IntoMap
    @ViewModelKey(RegisterNumberViewModel::class)
    abstract fun bindRegisterNumberViewModel(viewModel: RegisterNumberViewModel): ViewModel

    @Binds
    @MainScope
    @IntoMap
    @ViewModelKey(EnterPinViewModel::class)
    abstract fun bindEnterPinViewModel(viewModel: EnterPinViewModel): ViewModel

    @Binds
    @MainScope
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @MainScope
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    abstract fun bindDashboardViewModel(viewModel: DashboardViewModel): ViewModel

    @Binds
    @MainScope
    @IntoMap
    @ViewModelKey(SplashActivityViewModel::class)
    abstract fun bindSplashActivityViewModel(viewModel: SplashActivityViewModel): ViewModel

    @Binds
    @MainScope
    @IntoMap
    @ViewModelKey(NotificationsViewModel::class)
    abstract fun bindNotificationsViewModel(viewModel: NotificationsViewModel): ViewModel

}