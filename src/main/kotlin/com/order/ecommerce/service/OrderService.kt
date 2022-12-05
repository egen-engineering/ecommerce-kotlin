package com.order.ecommerce.service

import com.order.ecommerce.dto.OrderCreateResponse
import com.order.ecommerce.dto.OrderDto
import com.order.ecommerce.model.Order

interface OrderService {
    fun updateOrderStatus(orderId: String, orderStatus: String)
    fun findOrderById(orderId: String): OrderDto
    fun createOrder(orderDto: OrderDto): OrderCreateResponse
}
