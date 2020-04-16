package com.eebros.asan.data.remote.service

import com.eebros.asan.data.remote.request.TestRequestModel
import com.eebros.asan.data.remote.response.TestResponseModel
import com.eebros.asan.data.remote.provider.ServiceProvider
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