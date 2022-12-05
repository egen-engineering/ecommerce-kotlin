package com.order.ecommerce.service.impl

import com.order.ecommerce.dto.OrderCreateResponse
import com.order.ecommerce.dto.OrderDto
import com.order.ecommerce.enum.OrderStatus
import com.order.ecommerce.mapper.EntityMapper
import com.order.ecommerce.mapper.OrderDetailsMapper
import com.order.ecommerce.model.Order
import com.order.ecommerce.repository.OrderRepository
import com.order.ecommerce.service.OrderItemService
import com.order.ecommerce.service.OrderService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import javax.transaction.Transactional

@Service
class OrderServiceImpl(
    private val orderRepository: OrderRepository,
    private val orderDetailsMapper: OrderDetailsMapper,
    private val orderItemService: OrderItemService,
    private val mapper: EntityMapper
) : OrderService {

    companion object {
        val log: Logger = LoggerFactory.getLogger(OrderServiceImpl::class.java)
    }

    override fun updateOrderStatus(orderId: String, orderStatus: String) {
        val order: Order = orderRepository.findById(orderId).orElseThrow()
        order.orderStatus = orderStatus
        orderRepository.save(order)
    }

    override fun findOrderById(orderId: String): OrderDto {
        val order = orderRepository.findById(orderId).orElseThrow()
        return mapper.toOrderDto(order)
    }

    @Transactional
    override fun createOrder(orderDto: OrderDto): OrderCreateResponse {
        log.info("Creating Order for customer {}", orderDto.customerId)

        val savedOrder: Order =
            orderRepository.save(orderDto.toOrderEntity(UUID.randomUUID().toString()))

        orderItemService.createItems(savedOrder.orderId, orderDto.orderItems)
        return OrderCreateResponse(savedOrder.orderId, savedOrder.orderStatus)
    }

    private fun OrderDto.toOrderEntity(orderId: String) = Order(
        orderId = orderId,
        orderStatus = OrderStatus.PROCESSING.name,
        customerId = customerId,
        subTotal = subTotal,
        totalAmt = totalAmt,
        tax = tax,
        shippingCharges = shippingCharges,
        title = title,
        shippingMode = shippingMode,
        createdAt = LocalDateTime.now(),
        payment = orderDetailsMapper.buildPayment(amount, paymentMode),
        billingAddress = orderDetailsMapper.buildAddress(billingAddress),
        shippingAddress = orderDetailsMapper.buildAddress(shippingAddress),
        orderItems = null
    )
}
