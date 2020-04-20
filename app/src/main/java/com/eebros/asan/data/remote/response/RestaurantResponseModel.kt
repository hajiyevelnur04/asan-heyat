package com.eebros.asan.data.remote.response

data class RestaurantResponseModel(
    val name: String,
    val rating: Long,
    val deliveryTime: String,
    val minOrder: String,
    val offerText: String,
    val isOpen: Boolean
)