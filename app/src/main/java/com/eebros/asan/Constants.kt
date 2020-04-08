package com.eebros.asan

import com.eebros.asan.BuildConfig

class Constants{
    companion object{
        const val CHARSET = "Charset: UTF-8"
        const val BASE_URL = BuildConfig.BASIC_URL
        const val PEM_FILE = BuildConfig.PEM_FILE_NAME
        const val INTRO_DOTS: Int = 6
    }
}