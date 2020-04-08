package com.eebros.asan.local

import com.eebros.asan.R

open class ErrorMapper {

    companion object{
        val errorMap = hashMapOf<Int, Int>()
    }
    open fun getErrorMessage(errorCode: Int): Int{
        return if (errorMap[errorCode] === null){
            R.string.default_error_message
        } else {
            errorMap[errorCode]!!
        }
    }
    open fun saveDataToMap() {
        //Local errors
        errorMap[1] = R.string.app_name
        errorMap[2] = R.string.app_name
        errorMap[3] = R.string.app_name
        errorMap[4] = R.string.app_name
    }
}