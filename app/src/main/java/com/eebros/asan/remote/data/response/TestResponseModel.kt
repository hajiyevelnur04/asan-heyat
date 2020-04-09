package com.eebros.asan.remote.data.response

data class TestResponseModel(
    //100,200,300,404,500 etc
    val status: Int,
    val token: String
)
