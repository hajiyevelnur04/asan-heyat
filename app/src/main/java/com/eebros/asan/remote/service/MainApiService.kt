package com.eebros.asan.remote.service

import com.eebros.asan.remote.data.request.TestRequestModel
import com.eebros.asan.remote.data.response.TestResponseModel
import com.eebros.asan.remote.provider.ServiceProvider
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface MainApiService {
    @POST("/checkToken")
    fun checkToken(
        @Body checkCustomerRequestModel: TestRequestModel
    ): Single<TestResponseModel>
}

interface MainApiServiceProvider : ServiceProvider<MainApiService>