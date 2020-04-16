package com.eebros.asan.data.local

import com.eebros.asan.R

class ServiceImageMapper {
    companion object{
        val serviceMap = hashMapOf<String, Int>()
    }
    fun getCategoryImage(categoryName: String): Int{
        return if (serviceMap[categoryName]===null){
            0
        } else {
            serviceMap[categoryName]!!
        }
    }
    fun saveCategoryMapping() {

        serviceMap["asan təmizlik"] = R.mipmap.ic_launcher
        serviceMap["asan santexnik"] = R.mipmap.ic_launcher
        serviceMap["asan bərbər"] = R.mipmap.ic_launcher
        serviceMap["asan çatdirilma"] = R.mipmap.ic_launcher

    }
}