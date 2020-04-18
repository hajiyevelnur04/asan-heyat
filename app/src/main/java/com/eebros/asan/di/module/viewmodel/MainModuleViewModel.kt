package com.eebros.asan.di.module.viewmodel

import androidx.lifecycle.ViewModel
import com.eebros.asan.SplashActivityViewModel
import com.eebros.asan.di.scope.MainScope
import com.eebros.asan.di.scope.ViewModelKey
import com.eebros.asan.ui.activity.ComingSoonViewModel
import com.eebros.asan.ui.activity.PinRegisteredViewModel
import com.eebros.asan.ui.activity.PinRegistrationViewModel
import com.eebros.asan.ui.activity.delivery.FoodDeliveryViewModel
import com.eebros.asan.ui.activity.driver.MapsViewModel
import com.eebros.asan.ui.activity.main.MainViewModel
import com.eebros.asan.ui.activity.registration.DoneRegistrationViewModel
import com.eebros.asan.ui.activity.registration.NumberViewModel
import com.eebros.asan.ui.fragment.order.OrderViewModel
import com.eebros.asan.ui.fragment.home.HomeViewModel
import com.eebros.asan.ui.fragment.notifications.NotificationsViewModel
import com.eebros.asan.ui.fragment.order.RidesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainModuleViewModel {
    @Binds
    @MainScope
    @IntoMap
    @ViewModelKey(NumberViewModel::class)
    abstract fun bindNumberViewModel(viewModel: NumberViewModel): ViewModel

    @Binds
    @MainScope
    @IntoMap
    @ViewModelKey(FoodDeliveryViewModel::class)
    abstract fun bindFoodDeliveryViewModel(viewModel: FoodDeliveryViewModel): ViewModel

    @Binds
    @MainScope
    @IntoMap
    @ViewModelKey(ComingSoonViewModel::class)
    abstract fun bindComingSoonViewModel(viewModel: ComingSoonViewModel): ViewModel

    @Binds
    @MainScope
    @IntoMap
    @ViewModelKey(RidesViewModel::class)
    abstract fun bindRidesViewModel(viewModel: RidesViewModel): ViewModel

    @Binds
    @MainScope
    @IntoMap
    @ViewModelKey(MapsViewModel::class)
    abstract fun bindMapsViewModel(viewModel: MapsViewModel): ViewModel

    @Binds
    @MainScope
    @IntoMap
    @ViewModelKey(PinRegisteredViewModel::class)
    abstract fun bindPinRegisteredViewModel(viewModel: PinRegisteredViewModel): ViewModel

    @Binds
    @MainScope
    @IntoMap
    @ViewModelKey(PinRegistrationViewModel::class)
    abstract fun bindPinRegistrationViewModel(viewModel: PinRegistrationViewModel): ViewModel

    @Binds
    @MainScope
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @MainScope
    @IntoMap
    @ViewModelKey(DoneRegistrationViewModel::class)
    abstract fun bindDoneRegistrationViewModel(viewModel: DoneRegistrationViewModel): ViewModel


    @Binds
    @MainScope
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @MainScope
    @IntoMap
    @ViewModelKey(OrderViewModel::class)
    abstract fun bindDashboardViewModel(viewModel: OrderViewModel): ViewModel

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