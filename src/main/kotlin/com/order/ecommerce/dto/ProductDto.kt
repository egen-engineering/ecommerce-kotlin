package com.order.ecommerce.dto

data class ProductDto(
    val sku: String,
    val title: String,
    val description: String,
    val price: Double,
)
