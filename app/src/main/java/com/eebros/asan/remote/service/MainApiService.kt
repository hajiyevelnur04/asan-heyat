package com.eebros.asan.remote.service

import com.eebros.asan.remote.data.response.TestResponseModel
import com.eebros.asan.remote.provider.ServiceProvider
import io.reactivex.Observable
import retrofit2.http.GET

interface MainApiService {
    @GET("test.php")
    fun getTest(
    ): Observable<ArrayList<TestResponseModel>>
}

interface MainApiServiceProvider : ServiceProvider<MainApiService>