package com.order.ecommerce.dto

data class OrderCreateResponse(
    val orderId: String,
    val orderStatus: String
)
