package com.order.ecommerce.mapper

import com.order.ecommerce.dto.OrderDto
import com.order.ecommerce.dto.ProductDto
import com.order.ecommerce.model.Order
import com.order.ecommerce.model.Product
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
interface EntityMapper {
    fun toOrderDto(order: Order): OrderDto
    fun toProductDto(product: Product): ProductDto
}
