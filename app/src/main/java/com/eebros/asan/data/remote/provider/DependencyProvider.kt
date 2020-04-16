package com.eebros.asan.data.remote.provider

interface DependencyProvider<INSTANCE_TYPE> {
    fun getInstance(): INSTANCE_TYPE
}