package com.order.ecommerce.service

import com.order.ecommerce.dto.OrderItemDto

interface OrderItemService {
    fun createItems(orderId: String, items: List<OrderItemDto>)
}
