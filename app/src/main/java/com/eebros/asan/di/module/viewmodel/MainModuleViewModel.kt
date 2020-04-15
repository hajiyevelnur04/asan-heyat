package com.eebros.asan.di.module.viewmodel

import androidx.lifecycle.ViewModel
import com.eebros.asan.ui.activity.registration.MainActivityViewModel
import com.eebros.asan.SplashActivityViewModel
import com.eebros.asan.di.scope.MainScope
import com.eebros.asan.di.scope.ViewModelKey
import com.eebros.asan.ui.activity.PinActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainModuleViewModel {
    @Binds
    @MainScope
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(viewModel: MainActivityViewModel): ViewModel

    @Binds
    @MainScope
    @IntoMap
    @ViewModelKey(PinActivityViewModel::class)
    abstract fun bindPinActivityViewModel(viewModel: PinActivityViewModel): ViewModel

    @Binds
    @MainScope
    @IntoMap
    @ViewModelKey(SplashActivityViewModel::class)
    abstract fun bindSplashActivityViewModel(viewModel: SplashActivityViewModel): ViewModel

}