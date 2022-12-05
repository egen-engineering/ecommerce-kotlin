package com.order.ecommerce.dto

data class AddressDto(
    val address1: String,
    val address2: String,
    val city: String,
    val state: String,
    val zip: String,
    val email: String,
    val phone: String
)
