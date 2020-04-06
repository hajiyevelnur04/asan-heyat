package com.eebros.asan.repository

import com.eebros.asan.remote.data.response.TestResponseModel
import com.eebros.asan.remote.service.MainApiServiceProvider

import io.reactivex.Observable
import javax.inject.Inject

interface MainRepositoryType{
    fun getAllCards() : Observable<ArrayList<TestResponseModel>>
}

class MainRepository @Inject constructor(
    private val serviceProvider: MainApiServiceProvider
) : MainRepositoryType {

    override fun getAllCards() = serviceProvider.getInstance().getTest()
}