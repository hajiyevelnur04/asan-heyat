package com.eebros.asan.di.module.viewmodel

import androidx.lifecycle.ViewModel
import com.eebros.asan.MainActivityViewModel
import com.eebros.asan.di.scope.MainScope
import com.eebros.asan.di.scope.ViewModelKey
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

}