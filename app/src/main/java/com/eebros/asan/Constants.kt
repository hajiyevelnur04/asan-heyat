package com.eebros.asan

import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import com.eebros.asan.BuildConfig

class Constants{
    companion object{
        const val CHARSET = "Charset: UTF-8"
        const val BASE_URL = BuildConfig.BASIC_URL
        const val PEM_FILE = BuildConfig.PEM_FILE_NAME
        const val INTRO_DOTS: Int = 7
        const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }
}

fun animateProgressImage(progressImage: ArrayList<ImageView>) {
    progressImage.forEach{
        it.visibility = View.VISIBLE
    }
    val rotate = RotateAnimation(
        0f,
        360f,
        Animation.RELATIVE_TO_SELF,
        0.5f,
        Animation.RELATIVE_TO_SELF,
        0.5f
    )
    rotate.duration = 2000
    rotate.interpolator = LinearInterpolator()
    rotate.repeatCount = Animation.INFINITE
    progressImage.forEach{
        it.startAnimation(rotate)
    }
}