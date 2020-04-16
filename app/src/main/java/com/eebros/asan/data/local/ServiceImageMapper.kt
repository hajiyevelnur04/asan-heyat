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

        serviceMap["baby care"] = R.drawable.main_ic_baby_care
        serviceMap["beauty services"] = R.drawable.main_ic_beauty_services
        serviceMap["dog walking"] = R.drawable.main_ic_dog_walking
        serviceMap["pet care"] = R.drawable.main_ic_pet_care

        serviceMap["car wash"] = R.drawable.main_ic_car_wash
        serviceMap["car repair"] = R.drawable.main_ic_car_repair
        serviceMap["electronics"] = R.drawable.main_ic_electricians
        serviceMap["pest control"] = R.drawable.main_ic_pest_control


        serviceMap["bike ride"] = R.drawable.main_ic_bike_ride
        serviceMap["flowers delivery"] = R.drawable.main_ic_flowers_delivery
        serviceMap["courier service"] = R.drawable.main_ic_courier_service
        serviceMap["more"] = R.drawable.main_ic_more

    }
}