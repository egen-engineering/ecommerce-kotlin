package com.order.ecommerce.controller

import com.order.ecommerce.dto.OrderCreateResponse
import com.order.ecommerce.dto.OrderDto
import com.order.ecommerce.enum.OrderStatus
import com.order.ecommerce.service.OrderService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class OrderController(private val orderService: OrderService) {

    @PostMapping("/orders")
    @Operation(summary = "Create an order", description = "Create an order")
    fun createOrder(@RequestBody orderDto: OrderDto): OrderCreateResponse {
        return orderService.createOrder(orderDto)
    }

    @GetMapping("/orders/{orderId}")
    @Operation(summary = "Get an order", description = "Get an order")
    fun findOrderById(@PathVariable(name = "orderId") orderId: String): OrderDto {
        return orderService.findOrderById(orderId)
    }

    @PatchMapping("/orders/{orderId}")
    @Operation(summary = "Update status of the order", description = "Update status of the order")
    fun updateOrderStatus(
        @PathVariable("orderId") orderId: String,
        @RequestParam(name = "orderStatus") orderStatus: OrderStatus
    ) {
        orderService.updateOrderStatus(orderId, orderStatus.name)
    }
}
