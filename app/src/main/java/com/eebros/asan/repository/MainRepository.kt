package com.eebros.asan.repository

import com.eebros.asan.data.remote.request.TestRequestModel
import com.eebros.asan.data.remote.response.TestResponseModel
import com.eebros.asan.data.remote.service.MainApiServiceProvider

import io.reactivex.Single
import javax.inject.Inject

interface MainRepositoryType{
    fun checkToken(token: String, custId: Long): Single<TestResponseModel>
}

class MainRepository @Inject constructor(
    private val serviceProvider: MainApiServiceProvider
) : MainRepositoryType {

    override fun checkToken(
        token: String,
        custId: Long
    ): Single<TestResponseModel> {
        val request = TestRequestModel(token)
        return serviceProvider.getInstance().checkToken(request)
    }

}