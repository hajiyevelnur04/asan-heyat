package com.eebros.asan.di

import android.app.Application
import com.eebros.asan.base.BaseApplication
import com.eebros.asan.di.module.ActivityBuildersModule
import com.eebros.asan.di.module.AppModule
import com.eebros.asan.di.module.NetworkModule
import com.eebros.asan.di.module.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        AppModule::class,
        ViewModelFactoryModule::class,
        NetworkModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}