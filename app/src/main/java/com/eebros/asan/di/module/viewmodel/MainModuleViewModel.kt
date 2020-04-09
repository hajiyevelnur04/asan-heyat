package com.eebros.asan.di.module.viewmodel

import androidx.lifecycle.ViewModel
import com.eebros.asan.ui.activity.registration.RegisterNumberViewModel
import com.eebros.asan.SplashActivityViewModel
import com.eebros.asan.di.scope.MainScope
import com.eebros.asan.di.scope.ViewModelKey
import com.eebros.asan.ui.activity.EnterPinViewModel
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
    @ViewModelKey(SplashActivityViewModel::class)
    abstract fun bindSplashActivityViewModel(viewModel: SplashActivityViewModel): ViewModel

}