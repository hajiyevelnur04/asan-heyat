package com.eebros.asan.di.module.sub

import com.eebros.asan.repository.MainRepository
import com.eebros.asan.repository.MainRepositoryType
import com.eebros.asan.di.scope.MainScope
import com.eebros.asan.Constants.Companion.BASE_URL
import com.eebros.asan.remote.service.MainApiService
import com.eebros.asan.remote.service.MainApiServiceProvider
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

const val changeUrlName = "test"

@Module
class MainModule {
    @Provides
    @MainScope
    fun providesMainRepository(serviceProvider: MainApiServiceProvider): MainRepositoryType {
        return MainRepository(serviceProvider = serviceProvider)
    }

    @Provides
    @MainScope
    fun providesMainApiServiceProvider(@Named(changeUrlName) retrofit: Retrofit): MainApiServiceProvider =
        object : MainApiServiceProvider {
            val mainApiService = retrofit.create(MainApiService::class.java)
            override fun getInstance() = mainApiService
        }

    @Named(changeUrlName)
    @Provides
    @MainScope
    fun providesRetrofitInstance(
        callAdapterFactory: RxJava2CallAdapterFactory,
        converterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(callAdapterFactory)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()
    }
}