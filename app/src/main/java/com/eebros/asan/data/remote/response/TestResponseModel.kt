package com.eebros.asan.data.remote.response

data class TestResponseModel(
    //100,200,300,404,500 etc
    val status: Int,
    val token: String
)
