package com.order.ecommerce.service.impl

import com.order.ecommerce.dto.OrderItemDto
import com.order.ecommerce.model.OrderItem
import com.order.ecommerce.model.OrderItemPk
import com.order.ecommerce.repository.OrderItemRepository
import com.order.ecommerce.service.OrderItemService
import org.springframework.stereotype.Service

@Service
class OrderItemServiceImpl(
    private val orderItemRepository: OrderItemRepository
) : OrderItemService {
    override fun createItems(orderId: String, items: List<OrderItemDto>) {
        orderItemRepository.saveAll(buildOrderItems(items, orderId))
    }

    private fun buildOrderItems(
        orderItemsDtoList: List<OrderItemDto>,
        orderId: String
    ): List<OrderItem> = orderItemsDtoList.map { orderItemDto ->
        OrderItem(
            OrderItemPk(orderItemDto.productId, orderId),
            null,
            null,
            orderItemDto.quantity
        )
    }
}
